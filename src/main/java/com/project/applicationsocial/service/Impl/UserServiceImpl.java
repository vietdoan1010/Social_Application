package com.project.applicationsocial.service.Impl;

import aj.org.objectweb.asm.Handle;
import com.project.applicationsocial.model.DTO.UserDTO;
import com.project.applicationsocial.model.entity.Follows;
import com.project.applicationsocial.model.entity.Users;
import com.project.applicationsocial.model.mapper.UserMapper;
import com.project.applicationsocial.repository.FollowRepository;
import com.project.applicationsocial.repository.UserRepository;
import com.project.applicationsocial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.net.HttpRetryException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private FollowRepository followRepository;


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
    public void addFollow (UUID idUser, UUID idFollow) {
        Optional<Users>user = repository.findById(idUser);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Optional<Users>userFollowing = repository.findById(idFollow);
        if (userFollowing.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Optional<Follows> follow = followRepository.findFollowById(idUser, idFollow);
        if(follow.isPresent()) {
            throw new ResponseStatusException((HttpStatus.ALREADY_REPORTED));
        }

        Follows flow = new Follows();
        flow.setUserID(idUser);
        flow.setFollowingID(idFollow);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (flow.getCreatedAt() == null){
            flow.setCreatedAt(timestamp);
        }
        followRepository.save(flow);
    }



}