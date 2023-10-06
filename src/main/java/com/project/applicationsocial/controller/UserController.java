package com.project.applicationsocial.controller;

import com.project.applicationsocial.model.DTO.UserDTO;
import com.project.applicationsocial.model.entity.Users;
import com.project.applicationsocial.payload.repose.JwtReponse;
import com.project.applicationsocial.payload.request.LoginRequest;
import com.project.applicationsocial.payload.request.RegisterRequest;
import com.project.applicationsocial.repository.UserRepository;
import com.project.applicationsocial.service.UserDetail;
import com.project.applicationsocial.service.UserService;
import com.project.applicationsocial.service.jwt.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService service;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }

        Users user = new Users(registerRequest.getUsername(),
                passwordEncoder.encode(registerRequest.getPassword()),
                registerRequest.getFirstName(),
                registerRequest.getLastName(),
                registerRequest.getGender(),
                registerRequest.getPhoneNumber(),
                registerRequest.getDateOfBirth(),
                registerRequest.getEmail(),
                registerRequest.getAvatar());
        String strRoles = user.getRoles();
//        SimpleDateFormat time = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (strRoles == null) {
            user.setRoles("USER");
        }
        if (user.getEnable() == null) {
            user.setEnable(true);
        }
        if (user.getCreatedAt() == null) {
            user.setCreatedAt(timestamp);
        }
        if (user.getUpdatedAt() == null) {
            user.setUpdatedAt(timestamp);
        }
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully!");
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


    @GetMapping("/user/{username}")
    public ResponseEntity<Optional<Users>> getUserByName(@PathVariable String username) {
        Optional<Users> user = userRepository.findByUsername(username);
        return ResponseEntity.ok().body(user);

    }

    @GetMapping("/getAllUser")
    public ResponseEntity<?> findAll() {
        List<UserDTO> user = service.findAllUser();
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update/{id}")
    public Users updateUser(@PathVariable("id") UUID id, @RequestBody Users users) {
        return service.update(id, users);
    }




}