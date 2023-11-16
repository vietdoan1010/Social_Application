package com.project.applicationsocial.repository;

import com.project.applicationsocial.model.entity.Collections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CollectionsRepository extends JpaRepository<Collections, UUID> {

    List<Collections> getCollectionsByUserID(UUID userID);
    @Query(value = "select * from collections where user_id =:userID and collect_name=:collectName", nativeQuery = true)
    Collections getCollectionsByNameAndAndUserID(UUID userID, String collectName);
}
