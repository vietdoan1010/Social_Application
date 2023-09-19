package com.project.applicationsocial.repository;

import com.project.applicationsocial.entity.Medias;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MediasReponsitory extends CrudRepository<Medias, UUID> {

}
