package com.project.applicationsocial.service;

import com.project.applicationsocial.payload.repose.FileUploadReponse;
import com.project.applicationsocial.payload.request.DeleteFileRequest;
import com.project.applicationsocial.payload.request.UploadFileRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;


public interface FileService {
    FileUploadReponse addFile(UploadFileRequest uploadRequest) throws Exception;
    void deleteFile(DeleteFileRequest deleteRequst)throws Exception;

}
