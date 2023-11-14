package com.project.applicationsocial.repository;

import com.project.applicationsocial.model.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CommentsRepository extends JpaRepository<Comments, UUID> {
    @Query(value = "select * from comments where post_id =:postID", nativeQuery = true)
    List<Comments> findCommentsByPostID(UUID postID);
}
