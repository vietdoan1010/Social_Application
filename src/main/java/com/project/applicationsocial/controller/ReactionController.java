package com.project.applicationsocial.controller;

import com.project.applicationsocial.exception.ForbiddenException;
import com.project.applicationsocial.payload.request.ReactRequest;
import com.project.applicationsocial.payload.response.ResponseModel;
import com.project.applicationsocial.service.Impl.ReactionServiceImpl;
import com.project.applicationsocial.service.Impl.UserDetail;
import com.project.applicationsocial.service.ReactionService;
import org.apache.catalina.startup.ClassLoaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/react")
public class ReactionController {
    @Autowired
    ReactionService reactionService;
    @PostMapping(value = "/create",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> createReact(@AuthenticationPrincipal UserDetail userDetail,
                                         @ModelAttribute ReactRequest request
                                         )
    {
        if (userDetail == null) {
            throw new ForbiddenException("User is need login before posting!");
        }
        UUID id = userDetail.getId();
        reactionService.createReaction(id, request);
        ResponseModel responseModel = new ResponseModel<>();
        responseModel.setCode(200);
        responseModel.setData("Create react is success!");
        return ResponseEntity.ok().body(responseModel);
    }


    @DeleteMapping(value = "/delete/{objectID}")
    public ResponseEntity<?> deleteReact(@AuthenticationPrincipal UserDetail userDetail,
                                         @PathVariable(name = "objectID") UUID objectID)
    {
        if (userDetail == null) {
            throw new ForbiddenException("User is need login before posting!");
        }

        UUID id = userDetail.getId();
        reactionService.removeReaction(id, objectID);
        ResponseModel responseModel = new ResponseModel<>();
        responseModel.setCode(200);
        responseModel.setData("Delete react is success!");
        return ResponseEntity.ok().body(responseModel);
    }
}
