package com.project.applicationsocial.model.entity;

import com.project.applicationsocial.model.ENUM.ObjectTypeEnum;
import com.project.applicationsocial.model.ENUM.TypeReactEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reactions")
public class Reactions {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ID;

    @Column(name = "object_type")
    @Enumerated(EnumType.STRING)
    private ObjectTypeEnum objectType;

    @Column(name = "object_id")
    private UUID objectID;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TypeReactEnum type;

    @Column(name = "created_by")
    private UUID createdBy;

    public Reactions(ObjectTypeEnum objectType, UUID objectID, TypeReactEnum type) {
        this.objectType = objectType;
        this.objectID = objectID;
        this.type = type;
    }
}
