package org.perscholas.caseStudy.dao;

import org.perscholas.caseStudy.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "areas", path = "areas")
public interface IAreaRepository extends JpaRepository<Area, Long> {
}
