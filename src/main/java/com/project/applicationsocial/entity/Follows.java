package com.project.applicationsocial.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.repository.cdi.Eager;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "follows")
public class Follows {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "char(36)")
    private UUID ID;

    @Column(name = "following_user_id")
    private UUID followUserID;

    @Column(name = "created_at")
    private Timestamp createdID;
}