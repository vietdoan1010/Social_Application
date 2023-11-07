package com.project.applicationsocial.repository;

import com.project.applicationsocial.model.entity.Posts;
import com.project.applicationsocial.model.entity.Reactions;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ReactionRepository extends JpaRepository<Reactions, UUID> {
    @Query(value = "select * from reactions where created_by = :id and object_id = :objectID", nativeQuery = true)
    Reactions getReactByCreatedByAndObjectID(UUID id, UUID objectID);

}
