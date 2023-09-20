package com.project.applicationsocial.repository;

import com.project.applicationsocial.entity.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UsersRepository extends CrudRepository<Users, UUID> {
}
