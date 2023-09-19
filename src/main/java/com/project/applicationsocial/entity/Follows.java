package com.project.applicationsocial.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.repository.cdi.Eager;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "follows")
public class Follows {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID ID;

    @Column(name = "following_user_id")
    private UUID followUserID;

    @Column(name = "created_at")
    private Timestamp createdID;
}
