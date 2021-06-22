package org.perscholas.caseStudy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.perscholas.caseStudy.entity.House;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@AllArgsConstructor
public class GetUserHousesResponse {
    List<House> houses;
}
