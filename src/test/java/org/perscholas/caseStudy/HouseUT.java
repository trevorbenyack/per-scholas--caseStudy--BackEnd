package org.perscholas.caseStudy;

import org.junit.jupiter.api.*;
import org.modelmapper.ModelMapper;
import org.perscholas.caseStudy.dto.HouseDTO;
import org.perscholas.caseStudy.entity.House;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HouseUT {

    private static final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void checkExamMapping() {
        HouseDTO creation = new HouseDTO();
        creation.setHouseName("Testing houseName");
        creation.setStreetAddress01("Testing Street Address 01");
        creation.setStreetAddress02("Testing Street Address 02");
        creation.setCity("Testing city");
        creation.setState("Testing state");
        creation.setZipCode("Testing zipCode");

        House house = modelMapper.map(creation, House.class);
        assertEquals(creation.getHouseName(), house.getHouseName());
        assertEquals(creation.getStreetAddress01(), house.getStreetAddress01());
        assertEquals(creation.getStreetAddress02(), house.getStreetAddress02());
        assertEquals(creation.getCity(), house.getCity());
        assertEquals(creation.getState(), house.getState());
        assertEquals(creation.getZipCode(), house.getZipCode());

//        ExamUpdateDTO update = new ExamUpdateDTO();
//        update.setTitle("New title");
//        update.setDescription("New description");
//
//        modelMapper.map(update, exam);
//        assertEquals(update.getTitle(), exam.getTitle());
//        assertEquals(update.getDescription(), exam.getDescription());
//        assertEquals(creation.getCreatedAt(), exam.getCreatedAt());
//        assertEquals(update.getEditedAt(), exam.getEditedAt());
    }
}
