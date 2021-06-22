package org.perscholas.caseStudy.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONUtil;
import org.modelmapper.AbstractConverter;
import org.perscholas.caseStudy.entity.Area;
import org.perscholas.caseStudy.service.AreaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Data
@AllArgsConstructor
public class AreaIdsToAreasListConverter extends AbstractConverter<List<Area>, List<Area>> {

    AreaService areaService;

    @Override
    protected List<Area> convert(List<Area> areas) {

        // TODO Delete this
       log.info("submitted areas are: ");
       areas.forEach(a -> log.debug(a.toString()));

       log.info("entering areas stream");
        return areas
                .stream()
                .map(area -> {
                    Area areaAttached = areaService.getAreaById(area.getAreaId());
                    log.debug("area is " + area);
                    log.debug("areaAttached is " + areaAttached);
                    return area;
                })
                .collect(Collectors.toList());
    }

}
