package com.project.applicationsocial.service.Impl;
import com.project.applicationsocial.payload.repose.FileUploadReponse;
import com.project.applicationsocial.payload.request.DeleteFileRequest;
import com.project.applicationsocial.payload.request.UploadFileRequest;
import com.project.applicationsocial.service.FileService;
import com.project.applicationsocial.service.until.MinIOUntil;
import io.minio.MinioClient;
import org.jetbrains.annotations.NotNull;
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
    public void addFile(@NotNull UploadFileRequest uploadRequest) throws Exception {
        minioUtil.uploadFile(uploadRequest.getFile(),
                uploadRequest.getBuketName(), uploadRequest.getIdUser());
    }

    @Override
    public void deleteFile(@NotNull DeleteFileRequest deleteRequst) throws Exception {
        minioUtil.removeObject(deleteRequst.getBucketName(),
                deleteRequst.getObjectName(), deleteRequst.getIdUser());
    }



}
