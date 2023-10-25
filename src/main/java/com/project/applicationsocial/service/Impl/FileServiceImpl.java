package com.project.applicationsocial.service.Impl;
import com.project.applicationsocial.payload.request.DeleteFileRequest;
import com.project.applicationsocial.payload.request.UploadFileRequest;
import com.project.applicationsocial.service.FileService;
import com.project.applicationsocial.service.until.MinIOUntil;
import io.minio.MinioClient;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private MinIOUntil minioUtil;

    @Autowired
    private MinioClient client;

    @Autowired
    private MinIOUntil minIOUntil;

    @Override
    public void addFile(UploadFileRequest uploadRequest) throws Exception {
        minioUtil.uploadFile(uploadRequest.getFile(),
                uploadRequest.getBuketName(), uploadRequest.getIdUser());
    }

    @Override
    public void deleteFile(DeleteFileRequest deleteRequst) throws Exception {
        minioUtil.removeObject(deleteRequst.getBucketName(),
                deleteRequst.getObjectName(), deleteRequst.getIdUser());
    }

    @Override
    public void addListFile(MultipartFile[] files, String bucketName, UUID idUser) throws Exception {
        minioUtil.addListFile(files, bucketName, idUser);
    }


}
