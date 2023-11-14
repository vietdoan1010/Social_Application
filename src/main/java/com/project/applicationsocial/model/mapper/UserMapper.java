package com.project.applicationsocial.model.mapper;

import com.project.applicationsocial.model.DTO.UserDTO;
import com.project.applicationsocial.model.entity.Users;

public class UserMapper {
    public static UserDTO toUserDTO(Users user) {
        UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getFirstName());
            userDTO.setRoles(user.getRoles());
            userDTO.setEmail(user.getEmail());
            userDTO.setGender(user.getGender());
            userDTO.setPhoneNumber(user.getPhoneNumber());
            userDTO.setDateOfBirth(user.getDateOfBirth());
            userDTO.setEnable(user.getEnable());
            userDTO.setAvatar(user.getAvatar());
        return userDTO;
    }


}
