package org.perscholas.caseStudy.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.caseStudy.dto.HouseDTO;
import org.perscholas.caseStudy.entity.Area;
import org.perscholas.caseStudy.entity.House;
import org.perscholas.caseStudy.service.AreaService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@Slf4j
@RestController
@RequestMapping("/api/areas")
@AllArgsConstructor
public class AreaController {

    AreaService areaService;

    @PostMapping("/")
    public Area createArea(@RequestBody Area area) {
        log.debug("area is: " + area);
        return areaService.saveArea(area);
    } // end createHouse
}
