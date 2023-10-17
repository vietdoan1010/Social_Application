package com.project.applicationsocial.controller;

import com.project.applicationsocial.payload.repose.FileUploadReponse;
import com.project.applicationsocial.service.Impl.MinIOServiceImpl;
import com.project.applicationsocial.service.until.MinIOUntil;

import io.micrometer.common.util.StringUtils;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private MinIOServiceImpl minIOService;


    @PostMapping("/upload")
    public FileUploadReponse upload(@RequestParam(name = "file", required = false) MultipartFile file, @RequestParam(required = false) String bucketName) throws Exception {
        return minIOService.putObject(file,bucketName);
    }

    @DeleteMapping("/delete/{objectName}")
    public void delete(@PathVariable("objectName") String objectName, @RequestParam(required = false) String bucketName) throws Exception {
        minIOService.deleteFile(objectName, bucketName);
    }
}
