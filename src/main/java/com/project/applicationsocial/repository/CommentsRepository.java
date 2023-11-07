package com.project.applicationsocial.repository;

import com.project.applicationsocial.model.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentsRepository extends JpaRepository<Comments, UUID> {
}
