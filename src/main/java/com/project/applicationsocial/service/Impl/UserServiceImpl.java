package com.project.applicationsocial.service.Impl;

import com.project.applicationsocial.model.DTO.UserDTO;
import com.project.applicationsocial.model.entity.Users;
import com.project.applicationsocial.model.mapper.UserMapper;
import com.project.applicationsocial.repository.UserRepository;
import com.project.applicationsocial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;


    @Override
    public List<UserDTO> getAllUser() {
        return repository.findAll()
                .stream()
                .map(UserMapper::toUserDTO)
                .toList();
    }

    @Override
    public Optional<Users> findUserByName(String name) {
        return  repository.findByUsername(name);
    }


    @Override
    public Users update(UUID id, Users user) {
        Optional<Users> user1 = repository.findById(id);
        if(user1.isPresent()){
            return repository.save(user);
        }
        return null;
    }




    @Override
    public void addFollow (UUID userId, UUID idFollow) {

        Optional<Users> usersOptional = repository.findById(userId);
        if (usersOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not found");
        }
        Users user = usersOptional.get();

        Optional<Users> userFollowingOption = repository.findById(idFollow);
        if (userFollowingOption.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User following is not found");
        }
        Users usersFollow = userFollowingOption.get();
        Set<Users> listFollowing = user.getListIdFollow();

        if (listFollowing.contains(usersFollow)) {
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, "User has exist");
        }

        listFollowing.add(usersFollow);
        user.setListIdFollow(listFollowing);
        repository.save(user);
    }

    @Override
    public void unFollow(UUID userID, UUID idFollow) {
        Optional<Users> usersOptional = repository.findById(userID);
        if (usersOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User is not found");
        }
        Users user = usersOptional.get();
        Optional<Users> userFollowingOption = repository.findById(idFollow);
        if (userFollowingOption.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User following is not found");
        }

        Users usersFollow = userFollowingOption.get();
        Set<Users> listFollowing = user.getListIdFollow();

        if (!listFollowing.contains(usersFollow)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "User following is not found in list follow");
        }

        listFollowing.remove(usersFollow);
        user.setListIdFollow(listFollowing);
        repository.save(user);
    }


}