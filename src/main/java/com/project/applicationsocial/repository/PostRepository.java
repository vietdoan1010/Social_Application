package com.project.applicationsocial.repository;
import com.project.applicationsocial.model.entity.Posts;
import com.project.applicationsocial.model.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Posts, UUID> {
    @Query(value = "select p from Posts p where p.createdBy in :listFolowing")
    List<Posts> getPostsByCreatedBy(List<UUID> listFolowing, PageRequest pageRequest);


}
