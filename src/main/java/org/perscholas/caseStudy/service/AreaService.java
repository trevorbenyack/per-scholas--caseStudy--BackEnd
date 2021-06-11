package org.perscholas.caseStudy.service;

import lombok.AllArgsConstructor;
import org.perscholas.caseStudy.dao.IAreaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AreaService {

    IAreaRepository iAreaRepository;
}
