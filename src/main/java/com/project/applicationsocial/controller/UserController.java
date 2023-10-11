package com.project.applicationsocial.controller;

import com.project.applicationsocial.model.DTO.UserDTO;
import com.project.applicationsocial.model.entity.Users;
import com.project.applicationsocial.service.Impl.UserServiceImpl;
import com.project.applicationsocial.service.UserDetail;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;


    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByName(@PathVariable String username,  @RequestParam int size) {
        Page<Users> user =  userService.searchUserByName(username, size);
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

    @PostMapping("/addfollow/{idFl}")
    public ResponseEntity<?> addFollow(@AuthenticationPrincipal UserDetail userDetail, @PathVariable("idFl") UUID idFl) {
//        String userName = principal.getName();
        UUID userId = userDetail.getId();
//        UUID userName = userDetail.getId();
        if (userId != idFl) {
            userService.addFollow(userId, idFl);
            return ResponseEntity.ok().body("Success Fully");
        }
        return ResponseEntity.ok().body("Can not Follow");

    }

    @DeleteMapping(value = "/unFollow/{id}")
    public ResponseEntity<?> unFollow(@AuthenticationPrincipal UserDetail userDetail, @RequestParam("idUnFlow") UUID idUnFlow) {
        UUID userId = userDetail.getId();
        userService.unFollow(userId, idUnFlow);
        return ResponseEntity.ok().body("Success Fully");
    }




}