package com.project.applicationsocial.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "collections")
public class Collections {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "collect_name")
    private String collectName;

    @OneToMany(mappedBy = "collections", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Favorites> favorites = new ArrayList<>();

    public Collections(String collectName) {
        this.collectName = collectName;
    }
}
