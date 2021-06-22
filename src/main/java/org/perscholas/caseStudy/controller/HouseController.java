package org.perscholas.caseStudy.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.caseStudy.dto.HouseDTO;
import org.perscholas.caseStudy.entity.House;
import org.perscholas.caseStudy.service.HouseService;
import org.perscholas.caseStudy.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/houses")
@AllArgsConstructor
@Slf4j
public class HouseController {

    HouseService houseService;
    UserService userService;

//    @PostMapping("/addUserToHouse")
//    public void addUserToHouse(@RequestParam("userId") String userId, @RequestParam("houseId") String houseId) {
//        User user = userService.getUserById(Long.parseLong(userId));
//        House house = houseService.getHouseById(Long.parseLong(houseId));
//
//        houseService.addUserToHouse(user, house);
//
//    }

    @GetMapping("/")
    public House[] getAllHouses() {
        return houseService.getAllHouses();
    };

    @GetMapping("/{houseId}")
    public House getHouse(@PathVariable String houseId) {
        log.info("houseId is: " + houseId);
        House house = houseService.getHouseById(Long.parseLong(houseId));
        log.info("house is " + house);
        return house;
    }


    @PostMapping("/")
    public House createHouse(@RequestBody HouseDTO houseDTO) {
        House house = houseService.convertHouseDtoToEntity(houseDTO);
        return houseService.saveHouse(house);
    } // end createHouse
}
