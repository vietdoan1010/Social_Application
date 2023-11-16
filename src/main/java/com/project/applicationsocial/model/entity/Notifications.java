package com.project.applicationsocial.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notifications")
public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id")
    private UUID userID;

    @Column(name = "to_user")
    private UUID toUser;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    private Timestamp createdAt;

    public Notifications(UUID userID, UUID toUser, String content, Timestamp createdAt) {
        this.userID = userID;
        this.toUser = toUser;
        this.content = content;
        this.createdAt = createdAt;
    }
}
