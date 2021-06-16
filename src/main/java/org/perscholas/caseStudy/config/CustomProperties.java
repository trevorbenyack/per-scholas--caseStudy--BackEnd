package org.perscholas.caseStudy.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/*
 * Set up per:
 * https://www.baeldung.com/intellij-resolve-spring-boot-configuration-properties
 */

@Configuration
@ConfigurationProperties("org.perscholas")
@Getter @Setter
public class CustomProperties {

    /**
     * The server's home directory.
     */
    String baseDir;

    /**
     * Directory where photos are uploaded to.
     */
    String uploadDir;

    /**
     * Maximum size for photo uploads
     */
    int maxFileUploadSize;

}
