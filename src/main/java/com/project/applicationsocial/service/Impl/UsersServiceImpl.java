package com.project.applicationsocial.service.Impl;

import com.project.applicationsocial.entity.Users;
import com.project.applicationsocial.repository.UsersRepository;
import com.project.applicationsocial.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;
    @Override
    public void addUser(Users user) {
        usersRepository.save(user);
    }

    @Override
    public List<Users> getUsers() {
        return (List<Users>) usersRepository.findAll();
    }

    @Override
    public Optional<Users> getUser(UUID id) {
       return  usersRepository.findById(id);

    }

    @Override
    public Users updateUser(UUID id, Users users) {
        Optional<Users> users1 = usersRepository.findById(id);
        if(users1.isPresent()){
            return usersRepository.save(users);
        }
        return null;
    }


}
