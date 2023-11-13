package com.project.applicationsocial.controller;

import com.project.applicationsocial.exception.ForbiddenException;
import com.project.applicationsocial.payload.request.CommentRequest;
import com.project.applicationsocial.payload.response.ResponseModel;
import com.project.applicationsocial.service.Impl.UserDetail;
import com.project.applicationsocial.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    PostService postService;
    @PostMapping(value = "/add",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> addComment(@AuthenticationPrincipal UserDetail userDetail,
                                        @ModelAttribute CommentRequest commentRequest) throws Exception {
        if (userDetail == null) {
            throw new ForbiddenException("User is need login before comment post!");
        }
        String fullName = userDetail.getFirst_name() + " " + userDetail.getLast_name();
        postService.addComment(userDetail.getId(), commentRequest.getPostID(), commentRequest.getContent(), fullName);
        ResponseModel responseModel = new ResponseModel<>();
        responseModel.setCode(200);
        responseModel.setData("Comment post is success!");
        return ResponseEntity.ok().body(responseModel);
    }

    @DeleteMapping(value = "/remove/{idPost}/{idCmt}")
    public ResponseEntity<?> removeComment(@AuthenticationPrincipal UserDetail userDetail,
                                           @PathVariable(name = "idPost") UUID idPost,
                                           @PathVariable(name = "idCmt") UUID idCmt
    ) {
        if (userDetail == null) {
            throw new ForbiddenException("User is need login before comment post!");
        }
        postService.removeComment(userDetail.getId(), idPost, idCmt);
        ResponseModel responseModel = new ResponseModel<>();
        responseModel.setCode(200);
        responseModel.setData("Remove comment is success!");
        return ResponseEntity.ok().body(responseModel);
    }

    @PutMapping(value = "/update/{idCmt}",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> updateComment(@AuthenticationPrincipal UserDetail userDetail,
                                           @PathVariable(name = "idCmt") UUID idCmt,
                                           @ModelAttribute CommentRequest commentRequest
    ) {
        if (userDetail == null) {
            throw new ForbiddenException("User is need login before comment post!");
        }
        postService.updateComment(
                userDetail.getId(),
                idCmt,
                commentRequest.getPostID(),
                commentRequest.getContent()
        );
        ResponseModel responseModel = new ResponseModel<>();
        responseModel.setCode(200);
        responseModel.setData("Update comment is success!");
        return ResponseEntity.ok().body(responseModel);
    }

}
