package com.project.applicationsocial.controller;

import com.project.applicationsocial.model.DTO.UserDTO;
import com.project.applicationsocial.model.entity.Users;
import com.project.applicationsocial.payload.repose.PageResponse;
import com.project.applicationsocial.service.Impl.UserServiceImpl;
import com.project.applicationsocial.service.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;


    @GetMapping("/getUserByName")
    public ResponseEntity<?> getUserByName(
            @RequestParam String username, @RequestParam(defaultValue = "3") Integer size,
            @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "asc") String sort,
            @RequestParam(defaultValue = "user_name") String field)
    {
        Page<Users> user = userService.searchUserByName(username, size, page, sort, field);
        return ResponseEntity.ok().body(new PageResponse<Users>(
                user.getNumber(),
                (int) user.getTotalElements(),
                user.getSize(),
                user.getContent()));
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
        UUID userId = userDetail.getId();
        if (userId.equals(idFl)) {
            return ResponseEntity.badRequest().body("Cannot follow yourself");
        }
        userService.addFollow(userId, idFl);
        return ResponseEntity.ok().body("Success Fully");

    }

    @DeleteMapping(value = "/unFollow/{id}")
    public ResponseEntity<?> unFollow(@AuthenticationPrincipal UserDetail userDetail, @RequestParam("idUnFlow") UUID idUnFlow) {
        UUID userId = userDetail.getId();
        userService.unFollow(userId, idUnFlow);
        return ResponseEntity.ok().body("Success Fully");
    }




}