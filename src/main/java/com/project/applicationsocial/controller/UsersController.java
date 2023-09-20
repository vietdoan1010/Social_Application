package com.project.applicationsocial.controller;

import com.project.applicationsocial.entity.Users;
import com.project.applicationsocial.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;


    //Add User
    @PostMapping("/add")
    public String addUser(@RequestBody Users user) {
        usersService.addUser(user);
        return  "Add Success";
    }

    //Get All User
    @GetMapping("")
    public List<Users> getUsers(){
        return usersService.getUsers();
    }

    //Get One User
    @GetMapping("/get")
    public Optional<Users> getUser(@RequestParam UUID id) {
        return usersService.getUser(id);
    }

    //Update User width ID
    @PutMapping("/update/{id}")
    public Users updateUser(@PathVariable("id") UUID id, @RequestBody Users users) {
        return usersService.updateUser(id, users);
    }


}
