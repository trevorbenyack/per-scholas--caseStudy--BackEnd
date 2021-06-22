package org.perscholas.caseStudy.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.perscholas.caseStudy.entity.Area;

import javax.persistence.Id;
import java.util.List;

/*
* Based on:
*   http://modelmapper.org/user-manual/
*   https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application
*   https://www.baeldung.com/java-modelmapper-lists
 */

@Data
@NoArgsConstructor
public class HouseDTO {

    Long houseId;

    @NonNull
    String houseName;

    @NonNull
    String streetAddress01;

    String streetAddress02;

    @NonNull
    String city;

    @NonNull
    String state;

    @NonNull
    String zipCode;

    String notes;

    String pictureUrl;

    List<Area> areas;



}
