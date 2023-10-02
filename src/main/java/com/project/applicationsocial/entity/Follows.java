package com.project.applicationsocial.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@Table(name = "follows")
public class Follows {
    @Id
    @Column(name = "following_user_id")
    private UUID followingID;

    @Id
    @Column(name = "followed_user_id")
    private UUID followedID;

    @Column(name = "created_at")
    private Timestamp createdID;
}