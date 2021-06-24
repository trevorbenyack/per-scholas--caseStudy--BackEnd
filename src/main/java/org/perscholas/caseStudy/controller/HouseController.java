package org.perscholas.caseStudy.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.caseStudy.entity.House;
import org.perscholas.caseStudy.service.AreaService;
import org.perscholas.caseStudy.service.HouseService;
import org.perscholas.caseStudy.service.UserService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/houses")
@AllArgsConstructor
@Slf4j
public class HouseController {

    HouseService houseService;
    UserService userService;
    AreaService areaService;

    @GetMapping("/")
    public House[] getAllHouses() {
        return houseService.getAllHouses();
    }

    @GetMapping("/{houseId}")
    public House getHouse(@PathVariable String houseId) {
        log.info("houseId is: " + houseId);
        House house = houseService.getHouseById(Long.parseLong(houseId));
        log.info("house is " + house);
        return house;
    }

    @PostMapping("/")
    public House createHouse(@RequestBody House house) {
        return houseService.saveHouse(house);
    } // end createHouse

    @PutMapping("/")
    public House updateHouse(@RequestBody House house) {
        log.debug("updateHouse() has been called");
        return houseService.updateHouse(house);
    }
}
