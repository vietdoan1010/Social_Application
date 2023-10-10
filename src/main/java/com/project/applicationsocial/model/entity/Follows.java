package com.project.applicationsocial.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@IdClass(Follows.class)
@Table(name = "follows")
public class Follows {

    @Id
    @Column(name = "user_id")
    private UUID userID;

    @Id
    @Column(name = "following_user_id")
    private UUID followingID;


    @Column(name = "created_at")
    @CreatedDate
    private Timestamp createdAt;
}