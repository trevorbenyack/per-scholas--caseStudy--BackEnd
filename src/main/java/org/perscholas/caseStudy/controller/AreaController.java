package org.perscholas.caseStudy.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.caseStudy.dto.GetAllAreasResponse;
import org.perscholas.caseStudy.entity.Area;
import org.perscholas.caseStudy.entity.House;
import org.perscholas.caseStudy.service.AreaService;
import org.perscholas.caseStudy.service.HouseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@Slf4j
@RestController
@RequestMapping("/api/areas/")
@AllArgsConstructor
public class AreaController {

    AreaService areaService;
    HouseService houseService;

    @GetMapping("/")
    public GetAllAreasResponse getAllAreas() {
        return new GetAllAreasResponse(areaService.getAllAreas());
    }

    @PostMapping("/")
    public Area createArea(@RequestBody Area area) {
        log.debug("area is: " + area);
        return areaService.saveArea(area);
    } // end createHouse

    @GetMapping("/{areaId}")
    public Area getAreaById(@PathVariable String areaId) {
        Area area = areaService.getAreaById(Long.parseLong(areaId));
        log.info("area is: " + area);
        return area;
    }

    // TODO: Account for current user
    @DeleteMapping("/{areaId}")
    public ResponseEntity<String> deleteArea(@PathVariable String areaId) {

        House house = houseService.getHouseByAreaId(areaId);

        log.debug("House area will be deleted from is: " + house);

        Area area = areaService.getAreaById(Long.parseLong(areaId));
        house.removeArea(area);
        houseService.saveHouse(house);

        return ResponseEntity.ok("Area " + areaId + " deleted.");
    }
}
