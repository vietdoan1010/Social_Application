package com.project.applicationsocial.model.DTO;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Data
public class UserDTO implements Serializable {


    private UUID id;

    private String username;

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
