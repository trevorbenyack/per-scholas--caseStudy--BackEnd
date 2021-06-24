package org.perscholas.caseStudy.dao;

import org.perscholas.caseStudy.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestResource(collectionResourceRel = "areas", path = "areas")
public interface IAreaRepository extends JpaRepository<Area, Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Area a WHERE a.areaId = ?1")
    void deleteAreaById(Long areaId);
}
