package com.project.applicationsocial.service.until;

import com.project.applicationsocial.configs.MinIOConfig;
import com.project.applicationsocial.configs.MinioProp;
import com.project.applicationsocial.payload.repose.FileUploadReponse;
import io.minio.*;
import io.minio.errors.*;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Component
@Slf4j
public class MinIOUntil {

    @Autowired
    private MinioProp minioProp;

    @Autowired
    private MinioClient client;


    public void createBucket(String bucketName) throws Exception {
        if (!client.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
            client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }


    public FileUploadReponse uploadFile(MultipartFile file, String bucketName) throws Exception {
        if (null == file || 0 == file.getSize()) {
            return null;
        }
        createBucket(bucketName);
        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = bucketName + "_" +
                System.currentTimeMillis() + "_" + format.format(new Date()) + "_" + new Random().nextInt(1000) +
                originalFilename.substring(originalFilename.lastIndexOf("."));
        client.putObject(
                PutObjectArgs.builder().bucket(bucketName).object(fileName).stream(
                                file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build());
        String url = minioProp.getEndpoint() + "/" + bucketName + "/" + fileName;
        String urlHost = minioProp.getFilHost() + "/" + bucketName + "/" + fileName;
        log.info("upload is sussec ：[{}], urlHost ：[{}]", url, urlHost);
        return new FileUploadReponse(url, urlHost);
    }

    public void removeObject(String bucketName, String objectName) throws Exception {
        client.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }
}
