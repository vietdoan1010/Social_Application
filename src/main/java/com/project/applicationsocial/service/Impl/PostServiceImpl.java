package com.project.applicationsocial.service.Impl;

import com.project.applicationsocial.model.StatusEnum;
import com.project.applicationsocial.model.entity.Medias;
import com.project.applicationsocial.model.entity.Posts;
import com.project.applicationsocial.payload.request.PostRequest;
import com.project.applicationsocial.payload.response.FileUploadReponse;
import com.project.applicationsocial.repository.MediasRepository;
import com.project.applicationsocial.repository.PostRepository;
import com.project.applicationsocial.service.PostService;
import com.project.applicationsocial.service.until.MinIOUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.*;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    MediasRepository mediasRep;
    @Autowired
    MinIOUntil minIOUntil;
    @Override
    public void createPost(PostRequest postRequest, UUID idUser) throws Exception {
        Posts posts = new Posts(postRequest.getTitle(),
                postRequest.getBody());
        if (posts.getStatus() == null) {
            posts.setStatus(StatusEnum.PUBLIC);
        }

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (posts.getCreatedAt() == null) {
            posts.setCreatedAt(timestamp);
        }
        if (posts.getCreatedBy() == null) {
            posts.setCreatedBy(idUser);
        }
        String bucketName = "post";
        MultipartFile[] files = postRequest.getFiles();
        postRepository.save(posts);
        List<Medias> mediasList = new ArrayList<>();
        for (MultipartFile file : files) {
            FileUploadReponse lists =  minIOUntil.uploadFile(file,bucketName, idUser);
            Medias medias = new Medias();
            medias.setBaseName(file.getOriginalFilename());
            medias.setPublicURL(lists.getUrlHttp());
            medias.setPosts(posts);
            mediasList.add(medias);
        }
        mediasRep.saveAll(mediasList);
    }

    @Override
    public void deletePost(UUID idPost) {

    }
}
