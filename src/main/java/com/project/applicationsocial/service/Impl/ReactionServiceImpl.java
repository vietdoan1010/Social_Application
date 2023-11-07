package com.project.applicationsocial.service.Impl;

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
    public void removeReaction() {

    }
}
