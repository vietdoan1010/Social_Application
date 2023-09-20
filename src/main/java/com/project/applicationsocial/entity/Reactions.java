package com.project.applicationsocial.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Data
@Entity
@Table(name = "reactions")
public class Reactions {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "char(36)")
    private UUID ID;

    @Column(name = "object_type")
    private  String objectType;

    @Column(name = "object_id")
    private UUID objectID;

    @Column(name = "type")
    private Integer type;

    @Column(name = "created_by")
    private UUID createdID;
}
