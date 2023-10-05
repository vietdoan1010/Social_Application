package com.project.applicationsocial.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;


@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_name", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    //    @Enumerated(EnumType.STRING)
    @Column(name = "roles")
    private String roles;

    @Column(name = "gender", nullable = false)
    private Boolean gender;

    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "date_of_birth")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Timestamp dateOfBirth;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "enable")
    private Boolean enable;

    @Column(name = "created_at")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Timestamp createdAt;

    @JsonFormat(pattern = "yyyy/MM/dd")
    @Column(name = "updated_at")
    private Timestamp updatedAt;


    public Users(String username, String password, String firstName, String lastName, Boolean gender, String phoneNumber, Timestamp dateOfBirth, String email, String avatar) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.avatar = avatar;
    }


}
