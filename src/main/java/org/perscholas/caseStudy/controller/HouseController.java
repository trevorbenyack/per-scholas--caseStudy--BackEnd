package org.perscholas.caseStudy.controller;

import lombok.AllArgsConstructor;
import org.perscholas.caseStudy.entity.House;
import org.perscholas.caseStudy.entity.User;
import org.perscholas.caseStudy.service.HouseService;
import org.perscholas.caseStudy.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/houses")
@AllArgsConstructor
public class HouseController {

    HouseService houseService;
    UserService userService;

    @PostMapping("/addUserToHouse")
    public void addUserToHouse(@RequestParam("userId") String userId, @RequestParam("houseId") String houseId) {
        User user = userService.getUserById(Long.parseLong(userId));
        House house = houseService.getHouseById(Long.parseLong(houseId));

        houseService.addUserToHouse(user, house);

    }

}
