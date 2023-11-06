package com.project.applicationsocial.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comments implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ID;

    @Column(name = "content")
    private String content;

    @Column(name = "total_like")
    private int totalLike;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "created_by")
    private UUID  createdBy;


}