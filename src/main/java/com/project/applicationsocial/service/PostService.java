package com.project.applicationsocial.service;

import com.project.applicationsocial.model.entity.Posts;
import com.project.applicationsocial.payload.request.PostRequest;
import com.project.applicationsocial.payload.request.UpdatePostRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface PostService {

    List<Posts>getAllPost(UUID idUser);
    void createPost(PostRequest postRequest,  UUID idUser) throws Exception;
    void deletePost(UUID idPost, UUID idUser) throws Exception;

    void  updatePost(UUID idPost, UUID idUser, UpdatePostRequest updateRequest) throws Exception;
}
