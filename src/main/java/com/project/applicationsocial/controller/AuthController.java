package com.project.applicationsocial.controller;

import com.project.applicationsocial.model.entity.Users;
import com.project.applicationsocial.payload.request.LoginRequest;
import com.project.applicationsocial.payload.request.RegisterRequest;
import com.project.applicationsocial.service.Impl.UserServiceImpl;
import com.project.applicationsocial.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;
    @PostMapping("/signup")
    public Users registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        return userService.registerUser(registerRequest);
    }

    @PostMapping(  value = "/signin", consumes = "application/json")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }


}
