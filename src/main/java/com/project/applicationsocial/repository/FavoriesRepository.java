package com.project.applicationsocial.repository;

import com.project.applicationsocial.model.entity.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface FavoriesRepository extends JpaRepository<Favorites, UUID> {
    @Query(value = "select * from favorites where user_id =:userID and post_id=:postID ", nativeQuery = true)
    Favorites getFavoritesByUserIDAndPostID(UUID userID, UUID postID);
}
