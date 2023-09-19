package com.project.applicationsocial.repository;

import com.project.applicationsocial.entity.Reactions;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ReactionsReponsitory extends CrudRepository<Reactions, UUID> {
}
