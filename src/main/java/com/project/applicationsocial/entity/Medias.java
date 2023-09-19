package com.project.applicationsocial.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "medias")
public class Medias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID ID;

    @Column(name = "base_name")
    private String baseName;

    @Column(name = "public_url")
    private String publicURL;

    @Column(name = "post_id")
    private UUID postID;
}
