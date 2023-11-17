package com.project.applicationsocial.service.Impl;

import com.project.applicationsocial.exception.BadRequestException;
import com.project.applicationsocial.exception.NotFoundException;
import com.project.applicationsocial.model.DTO.UserDTO;
import com.project.applicationsocial.model.entity.Users;
import com.project.applicationsocial.model.mapper.UserMapper;
import com.project.applicationsocial.payload.request.LoginRequest;
import com.project.applicationsocial.payload.request.UserRequest;
import com.project.applicationsocial.payload.response.FileUploadReponse;
import com.project.applicationsocial.payload.response.JwtResponse;
import com.project.applicationsocial.repository.UserRepository;
import com.project.applicationsocial.service.UserService;
import com.project.applicationsocial.service.until.JwtUntil;
import com.project.applicationsocial.service.until.MinIOUntil;
import com.project.applicationsocial.service.until.PageUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

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

    @Autowired
    MinIOUntil minIOUntil;


    @Override
    @CacheEvict(value = "users", allEntries = true)
    public Users registerUser(UserRequest userRequest) {
        if (repository.existsByUsername(userRequest.getUsername())) {
            throw new BadRequestException("Error: User name is already in use!");
        }

        if(repository.existsByEmail(userRequest.getEmail())) {
            throw new BadRequestException("Error: Email is already in use!");
        }

        Users user = new Users(userRequest.getUsername(),
                passwordEncoder.encode(userRequest.getPassword()),
                userRequest.getFirstName(),
                userRequest.getLastName(),
                userRequest.getGender(),
                userRequest.getPhoneNumber(),
                userRequest.getDateOfBirth(),
                userRequest.getEmail()
        );
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
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = JwtUntil.generateJwtToken(authentication);

        UserDetail userDetail  = (UserDetail) authentication.getPrincipal();
        return ResponseEntity.ok().body(new JwtResponse(jwt,
                userDetail.getId(),
                userDetail.getUsername(),userDetail.getRoles(),
                userDetail.getFirst_name(), userDetail.getLast_name()));
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
    public Page<Users> searchUserByName(String username, Integer size, Integer page, String sort, String field) {
        return repository.findUserByName(username, PageUntil.parse( page,size, field, sort));
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public Users update(UUID id, UserRequest userRequest) {
        Optional<Users> usersOptional = repository.findById(id);
        Users user = usersOptional.get();
        if(usersOptional.isEmpty()){
            throw new NotFoundException("User is not found in system");
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setGender(userRequest.getGender());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setDateOfBirth(userRequest.getDateOfBirth());
        user.setEmail(userRequest.getEmail());
        user.setUpdatedAt(timestamp);
        return repository.save(user);
    }

    @Override
    public void updateAvt(UUID userID, MultipartFile file) throws Exception {
        Optional<Users> usersOptional = repository.findById(userID);
        Users user = usersOptional.get();
        if(usersOptional.isEmpty()){
            throw new NotFoundException("User is not found in system");
        }
        if (file != null) {
            String bucketName = "avatar";
            FileUploadReponse fileUpload =  minIOUntil.uploadFile(file,bucketName, userID);
            user.setAvatar(fileUpload.getUrlHttp());
            repository.save(user);
        }
    }

    @Override
    @Cacheable(value = "listFollow")
    public List<Users> getListFollow(UUID userID) {
        Optional<Users> usersOptional = repository.findById(userID);
        Users user = usersOptional.get();
        if(usersOptional.isEmpty()){
            throw new NotFoundException("User is not found in system");
        }

       return user.getListIdFollow();
    }


    @Override
    @CacheEvict(value = "listFollow", allEntries = true)
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
        List<Users> listFollowing = user.getListIdFollow();

        if (listFollowing.contains(usersFollow)) {
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, "User has exist");
        }

        listFollowing.add(usersFollow);
        user.setListIdFollow(listFollowing);
        repository.save(user);
    }

    @Override
    @CacheEvict(value = "listFollow", allEntries = true)
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
        List<Users> listFollowing =  user.getListIdFollow();

        if (!listFollowing.contains(usersFollow)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "User following is not found in list follow");
        }

        listFollowing.remove(usersFollow);
        user.setListIdFollow(listFollowing);
        repository.save(user);
    }

}