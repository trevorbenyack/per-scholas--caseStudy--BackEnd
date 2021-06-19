package org.perscholas.caseStudy.util;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.caseStudy.dao.IAreaRepository;
import org.perscholas.caseStudy.dao.IHouseRepository;
import org.perscholas.caseStudy.dao.IUserRepository;
import org.perscholas.caseStudy.entity.Area;
import org.perscholas.caseStudy.entity.House;
import org.perscholas.caseStudy.entity.User;
import org.perscholas.caseStudy.service.HouseService;
import org.perscholas.caseStudy.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@Slf4j
@Transactional
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {

    IUserRepository iUserRepository;
    IHouseRepository iHouseRepository;
    IAreaRepository iAreaRepository;
    UserService userService;
    HouseService houseService;

    @Override
    public void run(String... args) {
        log.info("Initializing db data");

        Area livingRoom = iAreaRepository.saveAndFlush(new Area("Living Room"));
        Area kitchen = iAreaRepository.saveAndFlush(new Area("Kitchen"));

        User user = iUserRepository.saveAndFlush(new User("trevorbenyack@gmail.com", "Trevor", "Benyack"));

        House house = iHouseRepository.saveAndFlush(new House("123 Wyomming Ave", "123 Wyomming Ave", "Pittsburgh", "PA", "12345"));

        house.addUser(user);
        livingRoom.setHouse(house);
        kitchen.setHouse(house);

        log.info("house's users are: ");
        house.getUsers().forEach(System.out::println);

        iUserRepository.saveAndFlush(new User("flokibenyackk@gmail.com", "Floki", "Benyack"));




    }

    @PostConstruct
    public void constructed(){
        log.trace("DataInitializer constructed");
    }

}
