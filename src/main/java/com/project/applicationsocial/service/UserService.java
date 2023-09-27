package com.project.applicationsocial.service;

import com.project.applicationsocial.Emun.RoleEnum;
import com.project.applicationsocial.entity.Users;
import com.project.applicationsocial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

//    @Autowired
//    PasswordEncoder passwordEncoder;

    public Users createUser(Users user) {
        if (user.getRoles() == null) {
            user.setRoles(RoleEnum.ROLE_USER);
        }
//        String password = passwordEncoder.encode(user.getPassword());
//        user.setPassword(password);
        return userRepository.save(user);
    }
}
