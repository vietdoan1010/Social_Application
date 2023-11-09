package com.project.applicationsocial.service;

import java.util.UUID;

public interface FavoritesService {
    void addFavorites(UUID userID,UUID postID, String collectName);
    void removeFavorites(UUID userID,UUID postID);
}
