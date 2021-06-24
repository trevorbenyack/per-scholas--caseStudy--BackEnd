package org.perscholas.caseStudy.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.perscholas.caseStudy.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Getter
@Setter
@Log
@AllArgsConstructor
public class IdentityService {

    UserService userService;

//    public User getCurrentUser() {
//
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String username;
//
//        if (principal instanceof UserDetails) {
//            username = ((UserDetails)principal).getUsername();
//            log.info("inside IdentityService > getCurrentUser() > if: username is " + username);
//        } else {
//            username = principal.toString();
//            log.info("inside else");
//        }
//
//        return userService.getUserbyId(Long.parseLong(username));
//    }
}
