package org.perscholas.caseStudy.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.caseStudy.entity.User;
import org.perscholas.caseStudy.exception.CopyFileException;
import org.perscholas.caseStudy.exception.FileTooLargeException;
import org.perscholas.caseStudy.exception.MissingFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@Slf4j
public class FileUtil {

    private static final String HOUSE_DIR = "houses";

    @Value("${org.perscholas.uploadDir}")
    public String userFilesBasePath;

    @Value("${org.perscholas.maxFileUploadSize}")
    private Long maxFileUploadSize;

    // originating method
    // validates file & then saves file to server
    public String copyFile(MultipartFile file, User user) throws MissingFileException, FileTooLargeException, CopyFileException {
        validateFile(file, maxFileUploadSize);
        String newFileName = saveFile(file, user);
        return newFileName;
    }

    // VALIDATION METHODS
    private void validateFile(MultipartFile file, Long maxFileUploadSize) throws MissingFileException, FileTooLargeException {
        checkFileExistence(file);
        checkFileSize(file, maxFileUploadSize);
    }

    public void checkFileSize(MultipartFile file, Long maxFileUploadSize) throws FileTooLargeException {
        if (file.getSize() > maxFileUploadSize) {
            String message = "File is too large - max size is " + maxFileUploadSize;
            throw new FileTooLargeException(message);
        }
    }

    public void checkFileExistence(MultipartFile file) throws MissingFileException {
        if (file == null) {
            throw new MissingFileException("No file sent!");
        }
        if (ObjectUtils.isEmpty(file.getName())) {
            throw new MissingFileException("No file sent!");
        }
    }

    // SAVING METHODS
    private String saveFile(MultipartFile file, User user) throws CopyFileException {
        try (InputStream is = file.getInputStream()) {
            String newFileName = generateUniqueFileName(file, user);
            Path rootLocation = Paths.get(getRootLocationForUserImageUpload(user));
            Files.copy(is, rootLocation.resolve(newFileName));

            return newFileName;
        } catch (IOException ie) {
            log.error("Problem uploading file!", ie);
            throw new CopyFileException("Failed to upload!");
        }
    }

    private String generateUniqueFileName(MultipartFile file, User user) {
        log.debug("Original file name is " + file.getOriginalFilename());

        String newFileName = FileNameUtil.createFileName(user, file.getOriginalFilename());
        log.debug("New file name is " + newFileName);

        return newFileName;
    }

    public String getRootLocationForUserImageUpload(User user) {
        if (user == null) {
            throw new IllegalArgumentException("No user provided!");
        }
        if (ObjectUtils.isEmpty(user.getUserId())) {
            throw new IllegalArgumentException("No user id!");
        }

        String location = userFilesBasePath +
                "/" +
                user.getUserId();

        createDirectoryIfItDoesntExist(location);

        return location;
    }

    private void createDirectoryIfItDoesntExist(String dir) {
        final Path path = Paths.get(dir);

        if (Files.notExists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException ie) {
                log.error("Problem creating directory " + dir);
            }
        }
    }


    public String properSeparators(String filePath) {
        if (filePath != null) {
            // return proper path
            return filePath.replaceAll("\\\\", "/");
        } else {
            return null;
        }
    }

    public Resource loadFileAsResource(String fileName) throws FileNotFoundException {

        String userId = "1";
        Path userPhotoDir = Paths.get(userFilesBasePath + "/" + userId)
                .toAbsolutePath().normalize();

        try {
            Path filePath = userPhotoDir.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                // throw new FileNotFoundException();
                log.error("File Not Found");
                log.info("userPhotoDir is " + filePath);
            }
        } catch (MalformedURLException e) {
            // throw new FileNotFoundException();
            log.error("File Not Found, MalformedURLException");
        }

        return null;
    }


}
