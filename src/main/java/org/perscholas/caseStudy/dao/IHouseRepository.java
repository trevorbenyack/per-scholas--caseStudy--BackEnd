package org.perscholas.caseStudy.dao;

import org.perscholas.caseStudy.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "houses", path = "houses")
public interface IHouseRepository extends JpaRepository<House, Long> {
}
