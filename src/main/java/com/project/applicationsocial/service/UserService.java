package com.project.applicationsocial.service;

import com.project.applicationsocial.DTO.UserDTO;
import com.project.applicationsocial.entity.Users;
import com.project.applicationsocial.mapper.UserMapper;
import com.project.applicationsocial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Users> userDetail = repository.findByUsername(username);

        // Converting userDetail to UserDetails
        return userDetail.map(UserDetail::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public String addUser(Users user) {
        if(user.getRoles() == null) {
            user.setRoles("USER");
        }
        if (user.getEnable() == null) {
            user.setEnable(true);
        }
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
        return "User Added Successfully";
    }

    public UserDTO getUserById(UUID id) {

        Users user = repository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Not found user with id = " + id));

        return UserMapper.toUserDTO(user);
    }


    public List<UserDTO> findAllUser() {
        return repository.findAll()
                .stream()
                .map(UserMapper::toUserDTO)
                .toList();
    }

//
//    public UserDTO update(UUID id, UserDTO user) {
//        Optional<Users> user1 = repository.findById(id);
//        if(user1.isPresent()){
//            return repository.save(user);
//        }
//        return null;
//    }


}