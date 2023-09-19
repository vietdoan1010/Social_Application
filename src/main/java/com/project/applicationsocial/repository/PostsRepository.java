package com.project.applicationsocial.repository;

import com.project.applicationsocial.entity.Posts;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PostsRepository extends CrudRepository<Posts, UUID> {
}
