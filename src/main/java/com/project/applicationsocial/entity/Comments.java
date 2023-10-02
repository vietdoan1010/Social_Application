package com.project.applicationsocial.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@Table(name = "comments")
public class Comments {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "char(36)")
    private UUID ID;

    @Column(name = "content")
    private String content;

    @Column(name = "post_id")
    private UUID postID;

    @Column(name = "total_like")
    private Number totalLike;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "created_by")
    private UUID  createdBy;
}