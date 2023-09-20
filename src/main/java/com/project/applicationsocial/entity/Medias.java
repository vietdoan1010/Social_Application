package com.project.applicationsocial.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Data
@Entity
@Table(name = "medias")
public class Medias {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "char(36)")
    private UUID ID;

    @Column(name = "base_name")
    private String baseName;

    @Column(name = "public_url")
    private String publicURL;

    @Column(name = "post_id")
    private UUID postID;
}