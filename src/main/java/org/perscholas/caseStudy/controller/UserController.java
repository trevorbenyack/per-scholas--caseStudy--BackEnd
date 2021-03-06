package org.perscholas.caseStudy.controller;

import lombok.AllArgsConstructor;
import org.perscholas.caseStudy.service.HouseService;
import org.perscholas.caseStudy.service.UserService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    UserService userService;
    HouseService houseService;

//    @PostMapping("/addHouseToUser")
//    public void addHouseToUser(@RequestParam("userId") String userId, @RequestParam("houseId") String houseId) {
//        User user = userService.getUserById(Long.parseLong(userId));
//        House house = houseService.getHouseById(Long.parseLong(houseId));
//
//        userService.addHouseToUser(user, house);
//    }

//    @GetMapping(path = "/{userId}/houses", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public ResponseEntity<GetUserHousesResponse> getUserHouses(@PathVariable String userId) {
//        User user = userService.getUserById(Long.parseLong(userId));
//        GetUserHousesResponse getUserHousesResponse = new GetUserHousesResponse(houseService.getHousesByUser(user));
//        return ResponseEntity.status(HttpStatus.OK).body(getUserHousesResponse);
//
//    }
}
