package org.perscholas.caseStudy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.perscholas.caseStudy.entity.Area;

import java.util.List;

@AllArgsConstructor
@Data
public class GetAllAreasResponse {
    List<Area> areas;
}
