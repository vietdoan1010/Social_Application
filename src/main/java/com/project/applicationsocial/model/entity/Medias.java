package com.project.applicationsocial.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medias")
public class Medias implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ID;

    @Column(name = "base_name")
    private String baseName;

    @Column(name = "public_url")
    private String publicURL;

}