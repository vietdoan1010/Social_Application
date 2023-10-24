package com.project.applicationsocial.repository;

import com.project.applicationsocial.model.entity.Medias;
import com.project.applicationsocial.model.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MediasRepository extends JpaRepository<Medias, UUID> {
    @Query(value = "select * from medias m where m.post_id = :idPost", nativeQuery = true)
    List<Medias> getMediasByPostID(UUID idPost);

}
