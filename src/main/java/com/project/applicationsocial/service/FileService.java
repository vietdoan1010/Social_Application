package com.project.applicationsocial.service;

import com.project.applicationsocial.payload.request.DeleteFileRequest;
import com.project.applicationsocial.payload.request.UploadFileRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;


public interface FileService {
    void addFile(UploadFileRequest uploadRequest) throws Exception;
    void deleteFile(DeleteFileRequest deleteRequst)throws Exception;

    void addListFile(MultipartFile[] files, String bucketName, UUID idUser) throws Exception;

}
