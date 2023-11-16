package com.project.applicationsocial.service.Impl;

import com.project.applicationsocial.exception.ForbiddenException;
import com.project.applicationsocial.exception.NotFoundException;
import com.project.applicationsocial.model.entity.Collections;
import com.project.applicationsocial.repository.CollectionsRepository;
import com.project.applicationsocial.service.CollectionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CollectionsServiceImpl implements CollectionsService {
    @Autowired
    CollectionsRepository collectionsRep;
    @Override
    @Cacheable(value = "collections")
    public List<Collections> getAllCollections(UUID userID) {
        List<Collections> collections = collectionsRep.getCollectionsByUserID(userID);
        if (collections.isEmpty()) {
            throw new NotFoundException("Collection is not found!");
        }
        return collections;
    }

    @Override
    @CacheEvict(value = "collections", allEntries = true)
    public void updateCollectionName(UUID userID ,String collectionName, String newNameCollection) {
        Collections collection = collectionsRep.getCollectionsByNameAndAndUserID(userID, collectionName);
        if (collection ==  null) {
            throw new NotFoundException("Collection is not found!");
        }
        collection.setCollectName(newNameCollection);
        collectionsRep.save(collection);
    }

    @Override
    @CacheEvict(value = "collections", allEntries = true)
    public void deleteCollection(UUID userID, UUID collectionID) {
        Optional<Collections> collectionsOptional = collectionsRep.findById(collectionID);

        if (collectionsOptional.isEmpty()) {
            throw new NotFoundException("Collection is not found!");
        }
        Collections collection = collectionsOptional.get();
        if (!collection.getUserID().equals(userID)) {
            throw new ForbiddenException("No permissions granted!");
        }
        collectionsRep.delete(collection);

    }
}
