package com.project.applicationsocial.DTO;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class UserDTO {
    private UUID id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String roles;

    private Boolean gender;

    private String phoneNumber;

    private Timestamp dateOfBirth;

    private String email;

    private String avatar;

    private Boolean enable;
}
