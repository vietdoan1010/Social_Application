package com.project.applicationsocial.service.Impl;

import com.project.applicationsocial.payload.repose.FileUploadReponse;
import com.project.applicationsocial.service.MinIOService;
import com.project.applicationsocial.service.until.MinIOUntil;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@Service
public class MinIOServiceImpl implements MinIOService {
    @Autowired
    private MinIOUntil minioUtil;


    @Override
    public FileUploadReponse putObject(MultipartFile file,String bucketName) throws Exception {
        FileUploadReponse response = null;
        if (StringUtils.isBlank(bucketName)) {
            bucketName = "images";
        }
            response = minioUtil.uploadFile(file, bucketName);
        return response;

    }

    @Override
    public void deleteFile(String objectName, String bucketName) throws Exception {
        if (StringUtils.isBlank(bucketName)) {
            bucketName = "images";
        }
        minioUtil.removeObject(bucketName, objectName);
        System.out.println("Delete success");
    }
}
