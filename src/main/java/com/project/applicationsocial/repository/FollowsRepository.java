package com.project.applicationsocial.repository;

import com.project.applicationsocial.entity.Follows;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface FollowsRepository extends CrudRepository<Follows, UUID> {
}
