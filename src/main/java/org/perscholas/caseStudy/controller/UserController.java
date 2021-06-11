package org.perscholas.caseStudy.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.perscholas.caseStudy.entity.House;
import org.perscholas.caseStudy.entity.User;
import org.perscholas.caseStudy.service.HouseService;
import org.perscholas.caseStudy.service.UserService;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    UserService userService;
    HouseService houseService;

    @PostMapping("/addHouseToUser")
    public void addHouseToUser(@RequestParam("userId") String userId, @RequestParam("houseId") String houseId) {
        User user = userService.getUserById(Long.parseLong(userId));
        House house = houseService.getHouseById(Long.parseLong(houseId));

        userService.addHouseToUser(user, house);

    }

}
