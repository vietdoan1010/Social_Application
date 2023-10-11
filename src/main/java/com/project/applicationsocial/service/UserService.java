package com.project.applicationsocial.service;

import com.project.applicationsocial.model.DTO.UserDTO;
import com.project.applicationsocial.model.entity.Users;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<UserDTO> getAllUser();

    Optional<Users> findUserByName(String name);

    Users update(UUID id, Users user);

    void addFollow(UUID userID,UUID idFollow);

    void unFollow(UUID userID, UUID idFollow);

}
