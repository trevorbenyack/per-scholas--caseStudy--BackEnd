package org.perscholas.caseStudy.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
@AllArgsConstructor
public class DataRestConfig implements RepositoryRestConfigurer {

    EntityManager entityManager;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry corsRegistry) {
        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, corsRegistry);

        corsRegistry
                .addMapping("/**")
                .allowedOrigins("http://localhost:4200");

        exposeIds(config);

    }

    // expose entity Ids in the response body
    private void exposeIds(RepositoryRestConfiguration config) {

        config.exposeIdsFor(entityManager
                .getMetamodel()
                .getEntities()
                .stream()
                .map(entityType -> entityType.getJavaType())
                .toArray(Class[]::new));
    }
}
