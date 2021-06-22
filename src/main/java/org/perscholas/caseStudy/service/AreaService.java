package org.perscholas.caseStudy.service;

import lombok.AllArgsConstructor;
import org.perscholas.caseStudy.dao.IAreaRepository;
import org.perscholas.caseStudy.entity.Area;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class AreaService {
    IAreaRepository iAreaRepository;

    public Area getAreaById(Long areaId) {
        return iAreaRepository.getById(areaId);
    }

    public Area saveArea(Area area) {
        return iAreaRepository.saveAndFlush(area);
    }
}
