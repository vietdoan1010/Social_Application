package com.project.applicationsocial.service.Impl;
import com.project.applicationsocial.payload.repose.FileUploadReponse;
import com.project.applicationsocial.payload.request.DeleteFileRequest;
import com.project.applicationsocial.payload.request.UploadFileRequest;
import com.project.applicationsocial.service.FileService;
import com.project.applicationsocial.service.until.MinIOUntil;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private MinIOUntil minioUtil;

    @Autowired
    private MinioClient client;

    @Autowired
    private MinIOUntil minIOUntil;

    @Override
    public FileUploadReponse addFile(UploadFileRequest uploadRequest) throws Exception {
        FileUploadReponse response =  minioUtil.uploadFile(uploadRequest.getFile(),
                uploadRequest.getBuketName(), uploadRequest.getIdUser());
        return response;
    }

    @Override
    public void deleteFile(DeleteFileRequest deleteRequst) throws Exception {
        minioUtil.removeObject(deleteRequst.getBucketName(),
                deleteRequst.getObjectName(), deleteRequst.getIdUser());
    }



}
