package com.project.applicationsocial.service.Impl;

import aj.org.objectweb.asm.Handle;
import com.project.applicationsocial.model.DTO.UserDTO;
import com.project.applicationsocial.model.entity.Follows;
import com.project.applicationsocial.model.entity.Users;
import com.project.applicationsocial.model.mapper.UserMapper;
import com.project.applicationsocial.repository.FollowRepository;
import com.project.applicationsocial.repository.UserRepository;
import com.project.applicationsocial.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.FormLoginDsl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.net.HttpRetryException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.Principal;
import java.sql.Array;
import java.sql.Timestamp;
import java.util.*;

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
    public void addFollow (UUID userId, UUID idFollow) {

        Optional<Users>user = repository.findById(userId);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Optional<Users>userFollowing = repository.findById(idFollow);
        if (userFollowing.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Optional<Follows> follow = followRepository.findFollowById(userId, idFollow);
        if(follow.isPresent()) {
            throw new ResponseStatusException((HttpStatus.ALREADY_REPORTED));
        }

        Follows flow = new Follows();
        flow.setUserID(userId);
        flow.setFollowingID(idFollow);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (flow.getCreatedAt() == null){
            flow.setCreatedAt(timestamp);
        }
        followRepository.save(flow);
    }

    @Override
    public void unFollow(UUID userID, UUID idFollow) {
        Optional<Follows>idUser = followRepository.findById(userID);
        if (idUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Optional<Follows>idUFolow = followRepository.findById(idFollow);
        if (idUFolow.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Optional<Follows> follow = followRepository.findFollowById(userID, idFollow);
        if(follow.isEmpty()) {
            throw new ResponseStatusException((HttpStatus.NOT_FOUND));
        }
        followRepository.deleteFollowById(userID,idFollow);



    }


}