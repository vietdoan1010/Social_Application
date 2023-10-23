package com.project.applicationsocial.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medias")
public class Medias {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ID;

    @Column(name = "base_name")
    private String baseName;

    @Column(name = "public_url")
    private String publicURL;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Posts posts;


}