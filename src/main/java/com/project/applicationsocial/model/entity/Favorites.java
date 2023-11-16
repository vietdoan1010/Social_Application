package com.project.applicationsocial.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "favorites")
public class Favorites implements Serializable {
    @Id
    @Column(name = "post_id")
    private UUID postID;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "user_id")
    private UUID userID;


    public Favorites(UUID postID, UUID userID) {
        this.postID = postID;
        this.userID = userID;
    }
}