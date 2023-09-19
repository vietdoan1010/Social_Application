package com.project.applicationsocial.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID ID;

    @Column(name = "content")
    private String content;

    @Column(name = "post_id")
    private UUID postID;

    @Column(name = "total_like")
    private Integer totalLike;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "created_by")
    private UUID  createdBy;
}
