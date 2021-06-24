package org.perscholas.caseStudy.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.perscholas.caseStudy.dao.IAreaRepository;
import org.perscholas.caseStudy.dao.IHouseRepository;
import org.perscholas.caseStudy.dao.IUserRepository;
import org.perscholas.caseStudy.dto.HouseDTO;
import org.perscholas.caseStudy.entity.Area;
import org.perscholas.caseStudy.entity.House;
import org.perscholas.caseStudy.entity.User;
import org.perscholas.caseStudy.service.HouseService;
import org.perscholas.caseStudy.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@Transactional
@AllArgsConstructor
public class AppInitializer implements CommandLineRunner {

    IUserRepository iUserRepository;
    IHouseRepository iHouseRepository;
    IAreaRepository iAreaRepository;
    UserService userService;
    HouseService houseService;
    private ModelMapper modelMapper;
    AreaIdsToAreasListConverter areaIdsToAreasListConverter;

    @Override
    public void run(String... args) {
        log.info("Initializing db data");

        Area livingRoom = iAreaRepository.saveAndFlush(new Area("Living Room"));
        Area kitchen = iAreaRepository.saveAndFlush(new Area("Kitchen"));

        User user = iUserRepository.saveAndFlush(new User("trevorbenyack@gmail.com", "Trevor", "Benyack"));

        House house01 = new House("123 Wyomming", "123 Wyomming Ave", "Pittsburgh", "PA", "12345");
        house01.setPictureUrl("https://media.gettyimages.com/photos/house-picture-id183881669?s=612x612");
        iHouseRepository.saveAndFlush(house01);

        user.addHouse(house01);
        house01.addArea(livingRoom);
        house01.addArea(kitchen);
        houseService.saveHouse(house01);

        House house02 = new House("456 Pennsyltucky", "456 Pennsyltucky Ave", "Pittsburgh", "PA", "54321");
        house02.addArea(new Area("bedroom"));
        house02.addArea(new Area("dining room"));
        house02.setPictureUrl("https://nextpittsburgh.com/wp-content/uploads/2019/11/woods2.jpg");

        houseService.saveHouse(house02);

        iUserRepository.saveAndFlush(new User("flokibenyackk@gmail.com", "Floki", "Benyack"));

    }
    

    @PostConstruct
    public void constructed(){
        log.trace("DataInitializer constructed");
    }

}
