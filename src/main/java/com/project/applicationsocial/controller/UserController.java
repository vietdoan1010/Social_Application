package com.project.applicationsocial.controller;

import com.project.applicationsocial.DTO.UserDTO;
import com.project.applicationsocial.entity.AuthRequest;
import com.project.applicationsocial.entity.Users;
import com.project.applicationsocial.payload.repose.JwtReponse;
import com.project.applicationsocial.payload.request.LoginRequest;
import com.project.applicationsocial.service.JwtService;
import com.project.applicationsocial.service.UserDetail;
import com.project.applicationsocial.service.UserService;
import com.project.applicationsocial.service.jwt.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService service;


    @Autowired
    private AuthenticationManager authenticationManager;

    @RolesAllowed("USER")
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }


    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody Users users) {
        return service.addUser(users);
    }

    @PostMapping(  value = "/signin", consumes = "application/json")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
         String jwt = JwtUtils.generateJwtToken(authentication);

        UserDetail userDetail  = (UserDetail) authentication.getPrincipal();

          return ResponseEntity.ok().body(new JwtReponse(jwt,
                  userDetail.getId(),
                  userDetail.getUsername(),userDetail.getRoles(), userDetail.getFirst_name(), userDetail.getLast_name()));
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

}