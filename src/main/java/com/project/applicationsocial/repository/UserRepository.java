package com.project.applicationsocial.repository;

import com.project.applicationsocial.model.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<Users, UUID> {
    Optional<Users> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query(value = "select * from users where user_name like CONCAT('%', :user_name, '%')", nativeQuery = true)
    Page<Users> findUserByName(String user_name, PageRequest pageRequest);

    @Query(value = "select following_user_id from follow where user_id = :id", nativeQuery = true)
    List<UUID> findAllFolowingByUserId(UUID id);
}
