package com.project.applicationsocial.service;

import com.project.applicationsocial.model.entity.Posts;
import com.project.applicationsocial.payload.request.PostRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface PostService {
    void createPost(PostRequest postRequest,  UUID idUser) throws Exception;
    void deletePost(UUID idPost);
}
