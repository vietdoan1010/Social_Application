package com.project.applicationsocial.service;

import com.project.applicationsocial.payload.repose.FileUploadReponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface MinIOService {
    FileUploadReponse putObject(MultipartFile file,String bucketName) throws Exception;

    void deleteFile(String objectName, String bucketName) throws  Exception;
}
