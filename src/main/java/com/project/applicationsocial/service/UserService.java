package com.project.applicationsocial.service;

import com.project.applicationsocial.model.DTO.UserDTO;
import com.project.applicationsocial.model.entity.Users;
import com.project.applicationsocial.payload.request.LoginRequest;
import com.project.applicationsocial.payload.request.UserRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface UserService {

    Users registerUser(UserRequest userRequest);

    ResponseEntity<?> login(LoginRequest loginRequest);
    List<UserDTO> getAllUser();

    Page<Users> searchUserByName(String username, Integer size, Integer page, String sort, String field);

    Users update(UUID id, UserRequest userRequest);

    void updateAvt(UUID userID, MultipartFile file) throws Exception;

    void addFollow(UUID userID,UUID idFollow);

    void unFollow(UUID userID, UUID idFollow);

}
