package com.project.applicationsocial.controller;

import com.project.applicationsocial.exception.ForbiddenException;
import com.project.applicationsocial.payload.request.PostRequest;
import com.project.applicationsocial.payload.response.ResponseModel;
import com.project.applicationsocial.service.Impl.PostServiceImpl;
import com.project.applicationsocial.service.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostServiceImpl postService;

    @PostMapping(value = "/create" ,consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<ResponseModel> createPost(@ModelAttribute PostRequest postRequest, @AuthenticationPrincipal UserDetail userDetail
                                        ) throws Exception {
        if (userDetail == null) {
            throw new ForbiddenException("User is need login before posting!");
        }

        UUID id = userDetail.getId();
        postService.createPost(postRequest, id);
        ResponseModel responseModel = new ResponseModel<>();
        responseModel.setCode(200);
        responseModel.setData("Create post is success!");
        return ResponseEntity.ok().body(responseModel);
    }
}
