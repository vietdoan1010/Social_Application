package com.project.applicationsocial.controller;

import com.project.applicationsocial.exception.ForbiddenException;
import com.project.applicationsocial.service.Impl.UserDetail;
import com.project.applicationsocial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/avatar")
public class AvatarController {
    @Autowired
    UserService userService;

    @PutMapping(value = "/update", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Transactional
    public ResponseEntity<?> updateAvt(@AuthenticationPrincipal UserDetail userDetail, @RequestParam(value = "file") MultipartFile file) throws Exception {
        if (userDetail == null) {
            throw new ForbiddenException("User is need login before update your info!");
        }
        userService.updateAvt(userDetail.getId(), file);
        return ResponseEntity.ok().body("Success Fully");
    }
}
