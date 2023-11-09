package com.project.applicationsocial.service.Impl;

import com.project.applicationsocial.exception.ForbiddenException;
import com.project.applicationsocial.exception.NotFoundException;
import com.project.applicationsocial.model.ENUM.ObjectTypeEnum;
import com.project.applicationsocial.model.entity.Comments;
import com.project.applicationsocial.model.entity.Posts;
import com.project.applicationsocial.model.entity.Reactions;
import com.project.applicationsocial.payload.request.ReactRequest;
import com.project.applicationsocial.repository.CommentsRepository;
import com.project.applicationsocial.repository.PostRepository;
import com.project.applicationsocial.repository.ReactionRepository;
import com.project.applicationsocial.service.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class ReactionServiceImpl implements ReactionService {
    @Autowired
    PostRepository postRep;

    @Autowired
    CommentsRepository commentsRep;

    @Autowired
    ReactionRepository reactionRep;
    @Override
    @Transactional
    @CacheEvict(value = "posts", allEntries = true)
    public void createReaction(UUID idUser, ReactRequest request) {
        Reactions reactions = new Reactions(request.getObjectType(),
                request.getObjectID(), request.getType());
        reactions.setCreatedBy(idUser);

        if (request.getObjectType().equals(ObjectTypeEnum.CMT)) {
            Optional<Comments> commentsOp = commentsRep.findById(request.getObjectID());
            if (commentsOp.isEmpty()) {
                throw new NotFoundException("Comment is not found!");
            }
            Reactions reaction = reactionRep.getReactByCreatedByAndObjectID(idUser, request.getObjectID());
            if (reaction == null) {
                Comments comments = commentsOp.get();
                comments.setTotalLike(+1);
                reactionRep.save(reactions);
                commentsRep.save(comments);
            }else {
                reaction.setType(request.getType());
                reactionRep.save(reaction);
            }
            return;
        }

        Optional<Posts> postsOptional = postRep.findById(request.getObjectID());
        if (postsOptional.isEmpty()) {
            throw new NotFoundException("Post is not found!");
        }
        Reactions reaction = reactionRep.getReactByCreatedByAndObjectID(idUser, request.getObjectID());
        if (reaction == null) {
            Posts post = postsOptional.get();
            post.setTotalLike(+1);
            reactionRep.save(reactions);
            postRep.save(post);
        }else {
            reaction.setType(request.getType());
            reactionRep.save(reaction);
        }
    }

    @Override
    @Transactional
    @CacheEvict(value = "posts", allEntries = true)
    public void removeReaction(UUID userID, UUID objectID) {
        Reactions reaction = reactionRep.getReactByCreatedByAndObjectID(userID,objectID);
        if (reaction == null) {
            throw new NotFoundException("Reaction is not found!");
        }
        if (!(reaction.getCreatedBy().equals(userID))) {
            throw new ForbiddenException("Not the Author!");
        }

        if (reaction.getObjectType() == ObjectTypeEnum.CMT) {
            Optional<Comments> commentsOp = commentsRep.findById(objectID);
            Comments comment = commentsOp.get();
            comment.setTotalLike(comment.getTotalLike()-1);
            commentsRep.save(comment);
            reactionRep.delete(reaction);
            return;
        }

        Optional<Posts> postsOp = postRep.findById(objectID);
        Posts post = postsOp.get();
        post.setTotalLike(post.getTotalLike()-1);
        postRep.save(post);
        reactionRep.delete(reaction);
    }
}
