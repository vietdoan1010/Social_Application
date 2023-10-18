package com.project.applicationsocial.controller;

import com.project.applicationsocial.payload.repose.FileUploadReponse;
import com.project.applicationsocial.payload.request.DeleteFileRequest;
import com.project.applicationsocial.payload.request.ListRequest;
import com.project.applicationsocial.payload.request.UploadFileRequest;
import com.project.applicationsocial.service.Impl.FileServiceImpl;

import com.project.applicationsocial.service.UserDetail;
import com.project.applicationsocial.service.until.MinIOUntil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileServiceImpl minIOService;

    @Autowired
    private MinIOUntil minIOUntil;


    @PostMapping("/addFileAvt")
    public FileUploadReponse addFileAvt(@AuthenticationPrincipal UserDetail userDetail,
                                    @RequestParam(value = "file") MultipartFile file) throws Exception {
        UUID userId = userDetail.getId();

        String bucketName = "avatar";
        return minIOService.addFile(new UploadFileRequest(file, bucketName, userId));
    }

    @DeleteMapping("/deleteFileAvt")
    public ResponseEntity<?> deleteFileAvt(@RequestParam(value = "object") String objectName, @AuthenticationPrincipal UserDetail userDetail) throws Exception {
        String bucketName = "avatar";
        UUID userId = userDetail.getId();
        minIOService.deleteFile(new DeleteFileRequest(bucketName,objectName, userId));
        return ResponseEntity.ok().body("Delete Success!");
    }

    @PostMapping("/addFilePost")
    public FileUploadReponse addFilePost(@AuthenticationPrincipal UserDetail userDetail,
                                        @RequestParam(value = "file") MultipartFile file) throws Exception {
        UUID userId = userDetail.getId();

        String bucketName = "post";
        return minIOService.addFile(new UploadFileRequest(file, bucketName, userId));
    }

    @DeleteMapping("/deleteFilePost")
    public ResponseEntity<?> deleteFilePost(@RequestParam(value = "object") String objectName, @AuthenticationPrincipal UserDetail userDetail) throws Exception {
        String bucketName = "post";
        UUID userId = userDetail.getId();
        minIOService.deleteFile(new DeleteFileRequest(bucketName,objectName, userId));
        return ResponseEntity.ok().body("Delete Success!");
    }

    @PostMapping("/addFileCmt")
    public FileUploadReponse addFileCmt(@AuthenticationPrincipal UserDetail userDetail,
                                        @RequestParam(value = "file") MultipartFile file) throws Exception {
        UUID userId = userDetail.getId();

        String bucketName = "comment";
        return minIOService.addFile(new UploadFileRequest(file, bucketName, userId));
    }

    @DeleteMapping("/deleteFileCmt")
    public ResponseEntity<?> deleteFileCmt(@RequestParam(value = "object") String objectName, @AuthenticationPrincipal UserDetail userDetail) throws Exception {
        String bucketName = "comment";
        UUID userId = userDetail.getId();
        minIOService.deleteFile(new DeleteFileRequest(bucketName,objectName, userId));
        return ResponseEntity.ok().body("Delete Success!");
    }

//    @DeleteMapping(value = "/deleteListFile")
//    public void getSubscribedPostFeed(@RequestBody ListRequest listRequest, @RequestParam(value= "bucketName") String bucketName) throws Exception {
//        minIOUntil.deleteListFile(listRequest.getList(), bucketName);
//    }









}
