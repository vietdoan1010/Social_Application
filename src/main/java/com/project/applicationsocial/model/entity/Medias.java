package com.project.applicationsocial.model.entity;

import jakarta.persistence.*;
import lombok.*;

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

//    @ManyToOne
//    @JoinColumn(name = "posts_id")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Posts posts;


}