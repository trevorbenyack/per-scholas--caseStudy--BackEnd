package org.perscholas.caseStudy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.caseStudy.entity.User;
import org.perscholas.caseStudy.exception.FileTooLargeException;
import org.perscholas.caseStudy.exception.MissingFileException;
import org.perscholas.caseStudy.payload.UploadFileResponse;
import org.perscholas.caseStudy.service.UserService;
import org.perscholas.caseStudy.util.FileUtil;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
* Photo upload built based on:
* https://careydevelopment.us/blog/how-to-support-image-uploading-with-preview-in-angular-and-spring-boot
*
* Photo url response based on:
* https://www.callicoder.com/spring-boot-file-upload-download-rest-api-example/
 */


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users/photos/")
public class ImageController {

    final FileUtil fileUtil;
    final UserService userService;

    // Responds with the image URL
    @PostMapping(path = "/uploadImage", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveProfileImage(@RequestParam("file") MultipartFile file) {

        // TODO: Change this to dynamically change to current user
//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    User user = (User)authentication.getPrincipal();
        String userId = "1";
        User user = userService.getUserById(Long.parseLong(userId));

        log.debug("User uploading is " + user.toString());

        try {

            String fileName = fileUtil.copyFile(file, user);

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/users/photos/")
                    .path(fileName)
                    .toUriString();

            log.debug("New file URL is : " + fileDownloadUri);

            UploadFileResponse uploadFileResponse = new UploadFileResponse(fileName, fileDownloadUri,
                    file.getContentType(), file.getSize());

            log.debug("upload file response is: " + uploadFileResponse);

            return ResponseEntity.status(HttpStatus.OK).body(uploadFileResponse);
        } catch (FileTooLargeException fe) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        } catch (MissingFileException me) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @GetMapping("/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws FileNotFoundException {
        // Load file as Resource
        Resource resource = fileUtil.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
