package com.project.applicationsocial.service.Impl;

import com.project.applicationsocial.exception.BadRequestException;
import com.project.applicationsocial.exception.NotFoundException;
import com.project.applicationsocial.model.DTO.UserDTO;
import com.project.applicationsocial.model.entity.Users;
import com.project.applicationsocial.model.mapper.UserMapper;
import com.project.applicationsocial.payload.request.LoginRequest;
import com.project.applicationsocial.payload.request.RegisterRequest;
import com.project.applicationsocial.payload.response.JwtResponse;
import com.project.applicationsocial.repository.UserRepository;
import com.project.applicationsocial.service.UserService;
import com.project.applicationsocial.service.until.JwtUntil;
import com.project.applicationsocial.service.until.PageUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    @CacheEvict(value = "users", allEntries = true)
    public Users registerUser(RegisterRequest request) {
        if (repository.existsByUsername(request.getUsername())) {
            throw new BadRequestException("Error: User name is already in use!");
        }

        if(repository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Error: Email is already in use!");
        }

        Users user = new Users(request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                request.getFirstName(),
                request.getLastName(),
                request.getGender(),
                request.getPhoneNumber(),
                request.getDateOfBirth(),
                request.getEmail(),
                request.getAvatar());
        String strRoles = user.getRoles();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (strRoles == null) {
            user.setRoles("USER");
        }
        if (user.getEnable() == null) {
            user.setEnable(true);
        }
        if (user.getCreatedAt() == null) {
            user.setCreatedAt(timestamp);
        }
        if (user.getUpdatedAt() == null) {
            user.setUpdatedAt(timestamp);
        }
        return repository.save(user);
    }

    @Override
    public ResponseEntity<?> login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = JwtUntil.generateJwtToken(authentication);

        UserDetail userDetail  = (UserDetail) authentication.getPrincipal();
        return ResponseEntity.ok().body(new JwtResponse(jwt,
                userDetail.getId(),
                userDetail.getUsername(),userDetail.getRoles(), userDetail.getFirst_name(), userDetail.getLast_name()));
    }

    @Override
    @Cacheable(value = "users")
    public List<UserDTO> getAllUser() {
        return repository.findAll()
                .stream()
                .map(UserMapper::toUserDTO)
                .toList();
    }


    @Override
    @Cacheable(value = "users", key = "#username")
    public Page<Users> searchUserByName(String username, Integer size, Integer page, String sort, String field) {
        return repository.findUserByName(username, PageUntil.parse( page,size, field, sort));
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public Users update(UUID id, Users user) {
        Optional<Users> user1 = repository.findById(id);
        if(user1.isPresent()){
            return repository.save(user);
        }
        throw new NotFoundException("User is not found in system");
    }


    @Override
    public void addFollow (UUID userId, UUID idFollow) {
        Optional<Users> usersOptional = repository.findById(userId);
        if (usersOptional.isEmpty()) {
            throw new NotFoundException("User is not found in system");
        }
        Users user = usersOptional.get();

        Optional<Users> userFollowingOption = repository.findById(idFollow);
        if (userFollowingOption.isEmpty()) {
            throw new NotFoundException("User is not found in system");
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