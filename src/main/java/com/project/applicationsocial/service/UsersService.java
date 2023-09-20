package com.project.applicationsocial.service;

import com.project.applicationsocial.entity.Users;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsersService {
    void addUser(Users users);

    List<Users> getUsers();

    Optional<Users> getUser(UUID id);

    Users updateUser(UUID id, Users users);
}
