package com.project.applicationsocial.repository;

import com.project.applicationsocial.model.entity.Medias;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MediasRepository extends JpaRepository<Medias, UUID> {
}
