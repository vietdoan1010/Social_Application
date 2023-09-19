package com.project.applicationsocial.repository;

import com.project.applicationsocial.entity.Comments;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CommentsRepository extends CrudRepository<Comments, UUID> {

}
