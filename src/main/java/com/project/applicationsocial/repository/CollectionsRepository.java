package com.project.applicationsocial.repository;

import com.project.applicationsocial.model.entity.Collections;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CollectionsRepository extends JpaRepository<Collections, UUID> {
}
