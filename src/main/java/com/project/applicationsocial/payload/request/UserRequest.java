package com.project.applicationsocial.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest implements Serializable {

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private Boolean gender;

    private String phoneNumber;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private Timestamp dateOfBirth;

    private String email;
}
