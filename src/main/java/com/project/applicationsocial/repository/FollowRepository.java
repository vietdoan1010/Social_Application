package com.project.applicationsocial.repository;

import com.project.applicationsocial.model.entity.Follows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FollowRepository extends JpaRepository<Follows,UUID> {
//    @Query(value = "select * from follows where user_id = cast(?1 as binary(16)) and following_user_id = cast(?2 as binary(16))", nativeQuery = true)
//    Optional<Follows> findFollowingID(String userLogin,String following);

    @Query(value = "select * from follows where user_id = ?1 and following_user_id = ?2", nativeQuery = true)
    Optional <Follows> findFollowById(UUID userLogin, UUID following);

}
