package com.project.applicationsocial.controller;

import com.project.applicationsocial.exception.ForbiddenException;
import com.project.applicationsocial.model.entity.Users;
import com.project.applicationsocial.service.Impl.UserDetail;
import com.project.applicationsocial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class FollowsController {
    @Autowired
    UserService userService;

    @GetMapping("/follows")
    public List<Users> getListFollow(@AuthenticationPrincipal UserDetail userDetail) {
        UUID userId = userDetail.getId();
        if (userDetail == null) {
            throw new ForbiddenException("User is need login!");
        }
        return userService.getListFollow(userId);

    }
    @PostMapping("/follow/{userID}")
    public ResponseEntity<?> addFollow(@AuthenticationPrincipal UserDetail userDetail, @PathVariable("userID") UUID userID) {
        UUID userId = userDetail.getId();
        if (userId.equals(userID)) {
            return ResponseEntity.badRequest().body("Cannot follow yourself");
        }
        userService.addFollow(userId, userID);
        return ResponseEntity.ok().body("Success Fully");

    }

    @DeleteMapping(value = "/unFollow/{userID}")
    public ResponseEntity<?> unFollow(@AuthenticationPrincipal UserDetail userDetail, @PathVariable("userID") UUID userID) {
        UUID userId = userDetail.getId();
        userService.unFollow(userId, userID);
        return ResponseEntity.ok().body("Success Fully");
    }
}
