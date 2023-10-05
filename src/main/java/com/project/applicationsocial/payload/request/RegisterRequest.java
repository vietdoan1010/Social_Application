package com.project.applicationsocial.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
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
}
