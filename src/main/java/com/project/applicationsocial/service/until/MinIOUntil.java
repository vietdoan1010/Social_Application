package com.project.applicationsocial.service.until;
import com.project.applicationsocial.configs.MinioProp;
import com.project.applicationsocial.exception.NotFoundException;
import com.project.applicationsocial.payload.response.FileUploadReponse;
import com.project.applicationsocial.repository.UserRepository;
import io.minio.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.InputStream;
import java.util.*;

@Component
@Slf4j
public class MinIOUntil {

    @Autowired
    private MinioProp minioProp;

    @Autowired
    private MinioClient client;

    @Autowired
    UserRepository userRepository;


    public void createBucket(String bucketName) throws Exception {
        if (!client.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
            client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    public FileUploadReponse uploadFile(MultipartFile file, String bucketName, UUID idUser) throws Exception {

         if (null == file || 0 == file.getSize()) {
            return null;
        }
        createBucket(bucketName);
        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        String fileName = bucketName + "_" +
                System.currentTimeMillis() + "_" + idUser + "_" + new Random().nextInt(1000) +
                originalFilename.substring(originalFilename.lastIndexOf("."));
        client.putObject(
                PutObjectArgs.builder().bucket(bucketName).object(fileName).stream(
                                file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build());
        String url = bucketName + "/" + fileName;

        log.info("upload is sussec ：[{}", url);
        return new FileUploadReponse(url);
    }

    public void removeObject(String bucketName, String objectName, UUID idUser) throws Exception {
        boolean found = client.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!found) {
            throw new NotFoundException("Bucket is not found in system!");
        }

       InputStream object =  getObject(bucketName, objectName);
        if (object == null) {
            throw new NotFoundException("Object is not found in system!");
        }

        String [] toArr = objectName.split("_");
        String getId = toArr[2];
        if (!getId.equals(idUser.toString())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        client.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }

     public void deleteListFile(List<String> listFile, String bucketName, UUID idUser) throws Exception {
        for (String result : listFile) {
            String [] toArr = result.split("_");
            String getId = toArr[2];
            if (!getId.equals(idUser.toString())) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }
            client.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(result).build());
        }
     }

     public InputStream getObject(String buketName, String objectName) throws  Exception{
       return client.getObject(GetObjectArgs.builder().bucket(buketName).object(objectName).build());
     }



     public List<FileUploadReponse> addListFile(MultipartFile[] listFile, String bucketName, UUID idUser) throws Exception {
         List url = new ArrayList();
        for (MultipartFile file : listFile) {
               if (null == file || 0 == file.getSize()) {
                   return null;
               }
               createBucket(bucketName);
               String originalFilename = file.getOriginalFilename();
               assert originalFilename != null;
               String fileName = bucketName + "_" +
                       System.currentTimeMillis() + "_" + idUser + "_" + new Random().nextInt(1000) +
                       originalFilename.substring(originalFilename.lastIndexOf("."));
               client.putObject(
                       PutObjectArgs.builder().bucket(bucketName).object(fileName).stream(
                                       file.getInputStream(), file.getSize(), -1)
                               .contentType(file.getContentType())
                               .build());
               url.add(bucketName + "/" + fileName);

               log.info("upload is sussec ：[{}", url);

           }
        return url;
     }

}
