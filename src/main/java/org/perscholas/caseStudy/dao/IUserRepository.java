package org.perscholas.caseStudy.dao;

import org.perscholas.caseStudy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface IUserRepository extends JpaRepository<User, Long> {
}
