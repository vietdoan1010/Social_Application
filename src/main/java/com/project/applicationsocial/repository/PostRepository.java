package com.project.applicationsocial.repository;
import com.project.applicationsocial.model.entity.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Posts, UUID> {
    @Query(value = "select * from posts where created_by = :id and status = 'PUBLIC' order by created_at", nativeQuery = true)
    List<Posts> getPostsByCreatedBy(UUID id);


}
