package com.project.applicationsocial.service;

import com.project.applicationsocial.payload.request.ReactRequest;

import java.util.UUID;

public interface ReactionService {
    void createReaction(UUID idUser, ReactRequest request);
    void removeReaction();
}
