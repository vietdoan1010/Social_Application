package com.project.applicationsocial.controller;

import com.project.applicationsocial.exception.ForbiddenException;
import com.project.applicationsocial.payload.response.ResponseModel;
import com.project.applicationsocial.service.Impl.UserDetail;
import com.project.applicationsocial.service.NotificationsService;
import com.project.applicationsocial.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/notifi")
public class NotificationsController {
    @Autowired
    NotificationsService notificationsService;
    @DeleteMapping(value = "/delete/{notifiID}")
    public ResponseEntity<?> deleteNotifi(@AuthenticationPrincipal UserDetail userDetail,
                                        @PathVariable(name = "notifiID") UUID notifiID) {
        if (userDetail == null) {
            throw new ForbiddenException("User is need login before comment post!");
        }
        notificationsService.deleteNotifi(userDetail.getId(),notifiID);
        ResponseModel responseModel = new ResponseModel<>();
        responseModel.setCode(200);
        responseModel.setData("Delete notifi is success!");
        return ResponseEntity.ok().body(responseModel);
    }
}
