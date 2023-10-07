package com.project.applicationsocial.repository;

import com.project.applicationsocial.model.DTO.UserDTO;
import com.project.applicationsocial.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<Users, UUID> {
    Optional<Users> findByUsername(String username);
    Optional<Users> findUsersById(UUID id);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
