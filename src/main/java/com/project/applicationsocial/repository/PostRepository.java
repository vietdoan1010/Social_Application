package com.project.applicationsocial.repository;

import com.project.applicationsocial.model.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Posts, UUID> {
}
