package com.project.applicationsocial.controller;

import com.project.applicationsocial.DTO.UserDTO;
import com.project.applicationsocial.entity.AuthRequest;
import com.project.applicationsocial.entity.Users;
import com.project.applicationsocial.service.JwtService;
import com.project.applicationsocial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody Users users) {
        return service.addUser(users);
    }


    //Get user by id
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUserById (@PathVariable UUID id) {
        UserDTO userDTO = service.getUserById(id);
        if(userDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<?> findAll() {
        List<UserDTO> user = service.findAllUser();
        return ResponseEntity.ok(user);
    }

//    @PutMapping("/update/{id}")
//    public Users updateUser(@PathVariable("id") UUID id, @RequestBody Users users) {
//        return service.update(id, users);
//    }

    @GetMapping("/user/profile")
    @PreAuthorize("hasAuthority('USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/profile")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @PostMapping("/generateToken")
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return ResponseEntity.status(400).body(jwtService.generateToken(authRequest.getUsername(), authRequest.getPassword()));
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
}