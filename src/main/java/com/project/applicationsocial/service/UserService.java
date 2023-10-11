package com.project.applicationsocial.service;

import com.project.applicationsocial.model.DTO.UserDTO;
import com.project.applicationsocial.model.entity.Users;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserDTO> getAllUser();

    Page<Users> searchUserByName(String username, int size);

    Users update(UUID id, Users user);

    void addFollow(UUID userID,UUID idFollow);

    void unFollow(UUID userID, UUID idFollow);

}
