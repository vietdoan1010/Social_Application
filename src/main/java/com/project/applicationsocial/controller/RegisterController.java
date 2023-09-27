package com.project.applicationsocial.controller;

import com.project.applicationsocial.entity.Users;
import com.project.applicationsocial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class RegisterController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody Users user) {
        userService.createUser(user);
        return  "Add Success";
    }

}
