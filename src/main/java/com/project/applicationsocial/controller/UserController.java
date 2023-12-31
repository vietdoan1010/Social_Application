package com.project.applicationsocial.controller;

import com.project.applicationsocial.exception.ForbiddenException;
import com.project.applicationsocial.model.DTO.UserDTO;
import com.project.applicationsocial.model.entity.Users;
import com.project.applicationsocial.payload.request.UserRequest;
import com.project.applicationsocial.payload.response.PageResponse;
import com.project.applicationsocial.service.Impl.UserDetail;
import com.project.applicationsocial.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<?> searchUserByName(
            @RequestParam(name = "username") String username, @RequestParam(defaultValue = "3") Integer size,
            @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "asc") String sort,
            @RequestParam(defaultValue = "user_name") String field)
    {
        Page<Users> user = userService.searchUserByName(username, size, page, sort, field);
        return ResponseEntity.ok().body(new PageResponse<>(
                user.getNumber(),
                (int) user.getTotalElements(),
                user.getSize(),
                user.getContent()));
    }

    @GetMapping("/list")
    public ResponseEntity<?> findAll() {
        List<UserDTO> user = userService.getAllUser();
        return ResponseEntity.ok(user);
    }

    @PutMapping(value = "/update/{id}")
    public Users updateUser(@AuthenticationPrincipal UserDetail userDetail,@PathVariable("id") UUID id,@RequestBody UserRequest userRequest) throws Exception {
        if (userDetail == null) {
            throw new ForbiddenException("User is need login before update your info!");
        }
        return userService.update(id, userRequest);
    }

    @PutMapping(value = "/update/avt", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Transactional
    public ResponseEntity<?> updateAvt(@AuthenticationPrincipal UserDetail userDetail, @RequestParam(value = "file") MultipartFile file) throws Exception {
        if (userDetail == null) {
            throw new ForbiddenException("User is need login before update your info!");
        }
        userService.updateAvt(userDetail.getId(), file);
        return ResponseEntity.ok().body("Success Fully");
    }

    @PostMapping("/follow/{idFl}")
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