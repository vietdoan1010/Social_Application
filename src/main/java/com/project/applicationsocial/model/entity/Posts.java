package com.project.applicationsocial.model.entity;

import com.project.applicationsocial.model.StatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@Table(name = "posts")
@AllArgsConstructor
@NoArgsConstructor
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ID;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Column(name = "total_like")
    private int totalLike;

    @Column(name = "total_comment")
    private  int totalComment;

    @Column(name = "created_by")
    private  UUID createdBy;

    @Column(name = "created_at")
    private Timestamp createdAt;


    public Posts(String title, String body) {
        this.title = title;
        this.body = body;
    }
}