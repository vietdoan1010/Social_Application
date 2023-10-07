package com.project.applicationsocial.controller;

import com.project.applicationsocial.model.DTO.UserDTO;
import com.project.applicationsocial.model.entity.Follows;
import com.project.applicationsocial.model.entity.Users;
import com.project.applicationsocial.repository.UserRepository;
import com.project.applicationsocial.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;


    @GetMapping("/{username}")
    public ResponseEntity<Optional<Users>> getUserByName(@PathVariable String username) {
        Optional<Users> user = userService.findUserByName(username);
        return ResponseEntity.ok().body(user);

    }

    @GetMapping("/getAllUser")
    public ResponseEntity<?> findAll() {
        List<UserDTO> user = userService.getAllUser();
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update/{id}")
    public Users updateUser(@PathVariable("id") UUID id, @RequestBody Users users) {
        return userService.update(id, users);
    }

    @PostMapping("/addfollow/{idUser}&{idFl}")
    public ResponseEntity<?> addFollow(@PathVariable("idUser") UUID idUser, @PathVariable("idFl") UUID idFl) {
        userService.addFollow(idUser, idFl);
        return ResponseEntity.ok().body("Success Fully");
    }




}