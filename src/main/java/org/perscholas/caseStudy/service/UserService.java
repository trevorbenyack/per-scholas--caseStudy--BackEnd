package org.perscholas.caseStudy.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.caseStudy.dao.IHouseRepository;
import org.perscholas.caseStudy.dao.IUserRepository;
import org.perscholas.caseStudy.entity.House;
import org.perscholas.caseStudy.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class UserService {

    IUserRepository iUserRepository;
    IHouseRepository iHouseRepository;

    public User getUserById(Long userId) {
        log.info("getUserById user Id is " + userId);
        return iUserRepository.getById(userId);
    }

    public void addHouseToUser(User user, House house) {

        user.addHouse(house);
        iUserRepository.saveAndFlush(user);

    }
}
