package com.project.applicationsocial.controller;

import com.project.applicationsocial.exception.ForbiddenException;
import com.project.applicationsocial.model.entity.Posts;
import com.project.applicationsocial.payload.request.CommentRequest;
import com.project.applicationsocial.payload.request.PostRequest;
import com.project.applicationsocial.payload.request.UpdatePostRequest;
import com.project.applicationsocial.payload.response.ResponseModel;
import com.project.applicationsocial.service.Impl.PostServiceImpl;
import com.project.applicationsocial.service.Impl.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostServiceImpl postService;


    @GetMapping(value = "/getAll")
    public ResponseEntity<List<Posts>> getAllPost(@AuthenticationPrincipal UserDetail userDetail,
                                                  @RequestParam(defaultValue = "3") Integer size,
                                                  @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "desc") String sort,
                                                  @RequestParam(defaultValue = "createdAt") String field) {
        if (userDetail == null) {
            throw new ForbiddenException("User is need login before posting!");
        }
        UUID id = userDetail.getId();
        return ResponseEntity.ok().body(postService.getAllPost(id,page,size, field, sort));
    }

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

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseModel> deletePost(@AuthenticationPrincipal UserDetail userDetail,@RequestParam("idPost") UUID idPost) throws Exception {
        if (userDetail == null) {
            throw new ForbiddenException("User is need login before delete post!");
        }
        postService.deletePost(idPost, userDetail.getId());
        ResponseModel responseModel = new ResponseModel<>();
        responseModel.setCode(200);
        responseModel.setData("Delete post is success!");
        return ResponseEntity.ok().body(responseModel);
    }

    @PutMapping(value="/update",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<ResponseModel> updatePost(@AuthenticationPrincipal UserDetail userDetail, @RequestParam("idPost") UUID idPost, @ModelAttribute UpdatePostRequest updateRequest) throws Exception {
        if (userDetail == null) {
            throw new ForbiddenException("User is need login before update post!");
        }
        postService.updatePost(idPost,userDetail.getId(),updateRequest);
        ResponseModel responseModel = new ResponseModel<>();
        responseModel.setCode(200);
        responseModel.setData("Update post is success!");
        return ResponseEntity.ok().body(responseModel);
    }


    @PostMapping(value = "/comment/create",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> addComment(@AuthenticationPrincipal UserDetail userDetail,
                                        @ModelAttribute CommentRequest commentRequest) throws Exception {
        if (userDetail == null) {
            throw new ForbiddenException("User is need login before comment post!");
        }
        postService.addComment(userDetail.getId(), commentRequest.getPostID(), commentRequest.getContent());
        ResponseModel responseModel = new ResponseModel<>();
        responseModel.setCode(200);
        responseModel.setData("Comment post is success!");
        return ResponseEntity.ok().body(responseModel);
    }

    @DeleteMapping(value = "/comment/remove/{idCmt}")
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

    @PutMapping(value = "/comment/update/{idCmt}",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
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
