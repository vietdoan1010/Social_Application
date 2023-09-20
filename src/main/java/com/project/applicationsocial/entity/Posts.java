package com.project.applicationsocial.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.w3c.dom.Text;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@Table(name = "posts")
public class Posts {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "char(36)")
    private UUID ID;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @Column(name = "status")
    private String status;

    @Column(name = "total_like")
    private Number totalLike;

    @Column(name = "total_comment")
    private  Number totalComment;

    @Column(name = "created_by")
    private  UUID createdBy;

    @Column(name = "created_at")
    private Timestamp createdAt;



}