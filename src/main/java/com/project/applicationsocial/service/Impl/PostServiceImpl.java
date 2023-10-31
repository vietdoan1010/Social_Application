package com.project.applicationsocial.service.Impl;

import com.project.applicationsocial.exception.NotFoundException;
import com.project.applicationsocial.model.StatusEnum;
import com.project.applicationsocial.model.entity.Medias;
import com.project.applicationsocial.model.entity.Posts;
import com.project.applicationsocial.model.entity.Users;
import com.project.applicationsocial.payload.request.PostRequest;
import com.project.applicationsocial.payload.request.UpdatePostRequest;
import com.project.applicationsocial.payload.response.FileUploadReponse;
import com.project.applicationsocial.repository.MediasRepository;
import com.project.applicationsocial.repository.PostRepository;
import com.project.applicationsocial.repository.UserRepository;
import com.project.applicationsocial.service.PostService;
import com.project.applicationsocial.service.until.MinIOUntil;
import com.project.applicationsocial.service.until.PageUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    UserRepository userRep;
    @Autowired
    MinIOUntil minIOUntil;


    @Override
    public List<Posts> getAllPost(UUID idUser, Integer page, Integer size, String field, String sort) {
        Optional<Users> users = userRep.findById(idUser);
        if(users.isEmpty()) throw new NotFoundException("User is not found!");
        List<UUID> usersFollow = new ArrayList<>();
        for (Users user : users.get().getListIdFollow()) {
             usersFollow.add(user.getId());
        }
        if (usersFollow == null || usersFollow.isEmpty()) {
            throw new NotFoundException("Not found user follow");
        }
        List<Posts> posts = postRepository.getPostsByCreatedBy(usersFollow, PageUntil.parse( page,size,field, sort));
        List<Posts> postsList = new ArrayList<>();
        for (Posts post : posts) {
            if (post.getStatus() == StatusEnum.PUBLIC) {
                postsList.add(post);
            }
        }

        return postsList;
    }

    @Override
    @Transactional
    public void createPost(PostRequest postRequest, UUID idUser) throws Exception {
        Posts posts = new Posts(postRequest.getTitle(),
                postRequest.getBody(), postRequest.getStatus());
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
        if (postRequest.getFiles() != null) {
            String bucketName = "post";
            MultipartFile[] files = postRequest.getFiles();
            postRepository.save(posts);
            List<Medias> mediasList = posts.getMedias();
            for (MultipartFile file : files) {
                FileUploadReponse lists =  minIOUntil.uploadFile(file,bucketName, idUser);
                Medias medias = new Medias();
                medias.setBaseName(file.getOriginalFilename());
                medias.setPublicURL(lists.getUrlHttp());
                mediasList.add(medias);
            }
            mediasRep.saveAll(mediasList);
        }
        postRepository.save(posts);

    }

    @Override
    @Transactional
    public void deletePost(UUID idPost,UUID idUser) throws Exception {
        Optional<Posts> postsOptional = postRepository.findById(idPost);
        if (postsOptional.isEmpty()) {
            throw new NotFoundException("Post is not found!");
        }
       List<Medias> mediasOptional = mediasRep.getMediasByPostID(idPost);
        List<String> fileList = new ArrayList<>();
        String bucketName = "post";
        for (Medias list : mediasOptional) {
           String url =  list.getPublicURL();
            String [] toArr = url.split("/");
            String objectName = toArr[1];
            fileList.add(objectName);
        }
        mediasRep.deleteAll(mediasOptional);
        postRepository.deleteById(idPost);
        minIOUntil.deleteListFile(fileList,bucketName, idUser);
    }

    @Override
    @Transactional
    public void updatePost(UUID idPost, UUID idUser, UpdatePostRequest updateRequest) throws Exception {
        Optional<Posts> postsOptional = postRepository.findById(idPost);
        if (postsOptional.isEmpty()) {
            throw new NotFoundException("Post is not found!");
        }
        Posts posts = postsOptional.get();
        posts.setTitle(updateRequest.getTitle());
        posts.setBody(updateRequest.getBody());
        posts.setStatus(updateRequest.getStatus());
        
        String bucketName = "post";
        List<String> urlFilesRemove = updateRequest.getRemoveFile();
        List<String> fileList = new ArrayList<>();
        List<Medias> fileMedia = new ArrayList<>();

        if (urlFilesRemove != null) {
            for (String urlFile : urlFilesRemove) {
                Medias mediasList = mediasRep.getMediasByPublicURL(urlFile);
                if (mediasList != null) {
                    String url = mediasList.getPublicURL();
                    String [] toArr = url.split("/");
                    String objectName = toArr[1];
                    fileList.add(objectName);
                    fileMedia.add(mediasList);
                }
            }
            mediasRep.deleteAll(fileMedia);
            minIOUntil.deleteListFile(fileList,bucketName,idUser);
        }

        if (updateRequest.getAddFile() != null) {
            MultipartFile[] files = updateRequest.getAddFile();
            postRepository.save(posts);
            List<Medias> mediasList = new ArrayList<>();
            for (MultipartFile file : files) {
                FileUploadReponse lists =  minIOUntil.uploadFile(file,bucketName, idUser);
                Medias medias = new Medias();
                medias.setBaseName(file.getOriginalFilename());
                medias.setPublicURL(lists.getUrlHttp());
                mediasList.add(medias);
            }
            mediasRep.saveAll(mediasList);
        }
        postRepository.save(posts);



    }

}
