package com.project.applicationsocial.service;

import com.project.applicationsocial.model.DTO.UserDTO;
import com.project.applicationsocial.model.entity.Users;
import com.project.applicationsocial.model.mapper.UserMapper;
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

    public List<UserDTO> findAllUser() {
        return repository.findAll()
                .stream()
                .map(UserMapper::toUserDTO)
                .toList();
    }


    public Users update(UUID id, Users user) {
        Optional<Users> user1 = repository.findById(id);
        if(user1.isPresent()){
            return repository.save(user);
        }
        return null;
    }


}