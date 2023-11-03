package com.project.applicationsocial.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.HashCodeExclude;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;



@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users implements Serializable {
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

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(
            name = "follow",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns =@JoinColumn(name = "following_user_id"))
       Set<Users> listIdFollow  = new HashSet<>();


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
