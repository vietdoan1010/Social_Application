package com.project.applicationsocial.model.entity;

import com.project.applicationsocial.model.StatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

@Data
@Entity
@Table(name = "posts")
@AllArgsConstructor
@NoArgsConstructor
public class Posts implements Serializable {
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

    @OneToMany(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinColumn(name = "post_id")
    private List<Medias> medias = new ArrayList<>();
    public Posts(String title, String body, StatusEnum status) {
        this.title = title;
        this.body = body;
        this.status = status;
    }
}