package com.project.applicationsocial.service.Impl;

import com.project.applicationsocial.exception.NotFoundException;
import com.project.applicationsocial.model.entity.Collections;
import com.project.applicationsocial.model.entity.Favorites;
import com.project.applicationsocial.model.entity.Posts;
import com.project.applicationsocial.model.entity.Users;
import com.project.applicationsocial.repository.CollectionsRepository;
import com.project.applicationsocial.repository.FavoriesRepository;
import com.project.applicationsocial.repository.PostRepository;
import com.project.applicationsocial.repository.UserRepository;
import com.project.applicationsocial.service.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FavoritesServiceImpl implements FavoritesService {
    @Autowired
    PostRepository postRep;
    @Autowired
    FavoriesRepository favoriesRep;
    @Autowired
    CollectionsRepository collectionsRep;
    @Autowired
    UserRepository userRep;

    @Override
    public void addFavorites(UUID userID, UUID postID, String collectName) {
        Optional<Posts> postsOptional = postRep.findById(postID);
        if (postsOptional.isEmpty()) {
            throw new NotFoundException("Post is not found!");
        }

        Collections collectionDB = collectionsRep.getCollectionsByNameAndAndUserID(userID, collectName);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Favorites favorites = new Favorites(postID,userID);
        if (favorites.getCreatedAt() == null) {
            favorites.setCreatedAt(timestamp);
        }
        favoriesRep.save(favorites);
        if (collectionDB == null) {
            List<Favorites> favoritesList = collectionDB.getFavorites();
            favoritesList.add(favorites);
            Collections collection = new Collections(collectName,userID, favoritesList);
            collectionsRep.save(collection);
        }else {
            collectionDB.getFavorites().add(favorites);
            List<Favorites> favoritesList = collectionDB.getFavorites();
            favoritesList.add(favorites);
            collectionDB.setUserID(userID);
            collectionDB.setFavorites(favoritesList);
            collectionsRep.save(collectionDB);
        }
    }

    @Override
    public void removeFavorites(UUID userID, UUID postID) {
        Optional<Posts> postsOptional = postRep.findById(postID);
        if (postsOptional.isEmpty()) {
            throw new NotFoundException("Post is not found!");
        }

        Favorites favorite = favoriesRep.getFavoritesByUserIDAndPostID(userID, postID);
        if (favorite == null) {
            throw new NotFoundException("Favorites is not found!");
        }

        favoriesRep.delete(favorite);
    }


    public List<Collections> getAllCollections() {
        return collectionsRep.findAll();
    }
}
