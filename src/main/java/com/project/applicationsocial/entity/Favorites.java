package com.project.applicationsocial.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@Table(name = "favorites")
public class Favorites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID postID;

    @Column(name = "user_id")
    private UUID userID;

    @Column(name = "created_at")
    private Timestamp createdAt;

}
