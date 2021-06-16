package org.perscholas.caseStudy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.caseStudy.entity.User;
import org.perscholas.caseStudy.exception.FileTooLargeException;
import org.perscholas.caseStudy.exception.MissingFileException;
import org.perscholas.caseStudy.service.UserService;
import org.perscholas.caseStudy.util.FileUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users/{userId}/photos/")
public class FileUploadController {

    final FileUtil fileUtil;
    final UserService userService;


    @PostMapping("/uploadHouseImage")
    public ResponseEntity<?> saveProfileImage(@RequestParam("file") MultipartFile file, @PathVariable String userId) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = (User)authentication.getPrincipal();
        User user = userService.getUserById(Long.parseLong(userId));
        log.info("User uploading is " + user.toString());

        try {
            fileUtil.copyFile(file, user);

            return ResponseEntity.ok().build();
        } catch (FileTooLargeException fe) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        } catch (MissingFileException me) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }
}
