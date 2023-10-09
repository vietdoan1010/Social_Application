package com.project.applicationsocial.controller;

import com.project.applicationsocial.model.DTO.UserDTO;
import com.project.applicationsocial.model.entity.Follows;
import com.project.applicationsocial.model.entity.Users;
import com.project.applicationsocial.repository.UserRepository;
import com.project.applicationsocial.service.Impl.AuthenticationImpl;
import com.project.applicationsocial.service.Impl.UserServiceImpl;
import com.project.applicationsocial.service.UserDetail;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    @RequestMapping(value = "/unFollow/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> unFollow(@AuthenticationPrincipal UserDetail userDetail, @RequestParam("idFlow") UUID idFlow) {
        UUID userId = userDetail.getId();
        userService.unFollow(userId, idFlow);
        return ResponseEntity.ok().body("Success Fully");
    }




}