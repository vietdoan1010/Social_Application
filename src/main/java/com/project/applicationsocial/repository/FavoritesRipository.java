package com.project.applicationsocial.repository;

import com.project.applicationsocial.entity.Favorites;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface FavoritesRipository extends CrudRepository<Favorites, UUID> {
}
