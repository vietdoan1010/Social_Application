package com.project.applicationsocial.service;

import com.project.applicationsocial.model.entity.Collections;

import java.util.List;
import java.util.UUID;

public interface CollectionsService {
    List<Collections> getAllCollections(UUID userID);

    void updateCollectionName(UUID userID,String collectionName, String newNameCollection);
    void deleteCollection(UUID userID,UUID collectionID);
}
