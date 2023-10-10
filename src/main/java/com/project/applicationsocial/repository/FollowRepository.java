package com.project.applicationsocial.repository;

import com.project.applicationsocial.model.entity.Follows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FollowRepository extends JpaRepository<Follows,UUID> {

    @Query(value = "select * from follows where user_id = ?1 and following_user_id = ?2", nativeQuery = true)
    Optional<Follows> findFollowById(UUID userLogin, UUID following);

    @Query(value = "select * from follows where user_id = ?1 ", nativeQuery = true)
    Optional<Follows> findFollowByIdUser(UUID userLogin);

    @Query(value = "select * from follows where following_user_id = ?1 ", nativeQuery = true)
    Optional<Follows> findFollowByIdFollow( UUID following);





    @Query(value = "delete from follows where user_id = ?1 and following_user_id = ?2", nativeQuery = true)
    Optional<Follows> deleteFollowById(UUID userLogin, UUID following);



}