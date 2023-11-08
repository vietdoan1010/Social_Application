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

    @Column(name = "user_id")
    private UUID userID;

    @OneToMany(fetch =FetchType.EAGER)
    @JoinColumn(name = "collection_id")
    private List<Favorites> favorites = new ArrayList<>();

    public Collections(String collectName, UUID userID, List<Favorites> favorites) {
        this.collectName = collectName;
        this.userID = userID;
        this.favorites = favorites;
    }
}
