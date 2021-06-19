package org.perscholas.caseStudy.service;

import lombok.AllArgsConstructor;
import org.perscholas.caseStudy.dao.IHouseRepository;
import org.perscholas.caseStudy.dao.IUserRepository;
import org.perscholas.caseStudy.entity.Area;
import org.perscholas.caseStudy.entity.House;
import org.perscholas.caseStudy.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HouseService {

    IUserRepository iUserRepository;
    IHouseRepository iHouseRepository;

    public House getHouseById(Long houseId) {
        return iHouseRepository.getById(houseId);
    }

    public List<House> getHousesByUser(User user) {
        return iHouseRepository.findByUsers(user);
    };

}
