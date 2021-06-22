package org.perscholas.caseStudy.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.caseStudy.dao.IAreaRepository;
import org.perscholas.caseStudy.entity.Area;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class AreaService {
    IAreaRepository iAreaRepository;

    public Area getAreaById(Long areaId) {
        return iAreaRepository.getById(areaId);
    }

    public List<Area> getAllAreas() {
        return iAreaRepository.findAll();
    }

    public Area saveArea(Area area) {
        return iAreaRepository.saveAndFlush(area);
    }

}
