package com.project.applicationsocial.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest implements Serializable {
    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private Boolean gender;

    private String phoneNumber;

    private Timestamp dateOfBirth;

    private String email;

    private String avatar;
}
