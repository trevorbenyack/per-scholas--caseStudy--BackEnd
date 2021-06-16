package org.perscholas.caseStudy.util;

import org.perscholas.caseStudy.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Service
public class FileNameUtil {

    // returns a string based on userID & current time
    public static String createUserTimeString(User user) {

        if (user == null) {
            throw new IllegalArgumentException("User cannot be null!");
        }
        if (ObjectUtils.isEmpty(user.getUserId())) {
            throw new IllegalArgumentException("User ID cannot be null!");
        }

        long currentTime = System.currentTimeMillis();

        return user.getUserId().toString() + "-" + currentTime;

    }

    // returns a string to be used as a unique fileName based on current user and current time
    public static String createFileName(User user, String originalFileName) {
        String userTimeString = createUserTimeString(user);
        String fileName;

        fileName = appendExtensionFromOriginalFileName(userTimeString, originalFileName);

        return fileName;
    }

    public static String appendExtensionFromOriginalFileName(String fileName, String originalFileName) {
        if (ObjectUtils.isEmpty(fileName)) {
            throw new IllegalArgumentException("File name can't be null!");
        }
        if (ObjectUtils.isEmpty(originalFileName)) {
            throw new IllegalArgumentException("Original file name can't be null!");
        }

        StringBuilder builder = new StringBuilder(fileName);
        if (!fileName.endsWith(".")) builder.append(".");

        String currentExtension = getCurrentExtensionFromFileName(originalFileName);
        builder.append(currentExtension);

        // return fileName
        return builder.toString();
    }


    public static String getCurrentExtensionFromFileName(String fileName) {
        if (ObjectUtils.isEmpty(fileName)) {
            throw new IllegalArgumentException("File name can't be null!");
        }
        if (!fileName.contains(".")) {
            throw new IllegalArgumentException("File name doesn't have an extension!");
        }

        int lastPeriodLoc = fileName.lastIndexOf(".");

        // return extension
        return fileName.substring(lastPeriodLoc + 1);

    }

    public static boolean fileNameHasSpecialChars(String fileName) {
        if (ObjectUtils.isEmpty(fileName)) {
            throw new IllegalArgumentException("File name can't be null!");
        }

        return false;
    }
}
