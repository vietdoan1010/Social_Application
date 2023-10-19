package com.project.applicationsocial.controller;
import com.project.applicationsocial.payload.repose.FileUploadReponse;
import com.project.applicationsocial.payload.request.DeleteFileRequest;
import com.project.applicationsocial.payload.request.UploadFileRequest;
import com.project.applicationsocial.service.Impl.FileServiceImpl;
import com.project.applicationsocial.service.UserDetail;
import com.project.applicationsocial.service.until.MinIOUntil;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileServiceImpl minIOService;

    @Autowired
    private MinIOUntil minIOUntil;


    @PostMapping(value = "/addFileAvt", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> addFileAvt(@AuthenticationPrincipal UserDetail userDetail,
                                        @RequestParam(value = "file") MultipartFile file ) throws Exception {
        try {
            String bucketName = "avatar";
            UUID idUser = userDetail.getId();
            minIOService.addFile(new UploadFileRequest(file, bucketName, idUser));
            return ResponseEntity.ok().body("Upload avatar success!");
        }catch (NullPointerException e) {
           return ResponseEntity.badRequest().body("UnAuthorized");
        }
    }

    @DeleteMapping("/deleteFileAvt")
    public ResponseEntity<?> deleteFileAvt(@RequestParam(value = "object") String objectName, @AuthenticationPrincipal UserDetail userDetail) throws Exception {
       try {
           String bucketName = "avatar";
           UUID userId = userDetail.getId();
           minIOService.deleteFile(new DeleteFileRequest(bucketName,objectName, userId));
           return ResponseEntity.ok().body("Delete Success!");
       }catch (NullPointerException e) {
           return ResponseEntity.badRequest().body("UnAuthorized");
       }

    }

    @PostMapping(value = "/addFilePost", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> addFilePost(@NotNull @AuthenticationPrincipal UserDetail userDetail,
                                              @RequestParam(value = "file") MultipartFile file) throws Exception {
        try {
            String bucketName = "post";
            UUID idUser = userDetail.getId();
            minIOService.addFile(new UploadFileRequest(file, bucketName, idUser));
            return ResponseEntity.ok().body("Upload avatar success!");
        }catch (NullPointerException e) {
            return ResponseEntity.badRequest().body("UnAuthorized");
        }
    }

    @DeleteMapping("/deleteFilePost")
    public ResponseEntity<?> deleteFilePost(@RequestParam(value = "object") String objectName, @NotNull @AuthenticationPrincipal UserDetail userDetail) throws Exception {
        try {
            String bucketName = "post";
            UUID userId = userDetail.getId();
            minIOService.deleteFile(new DeleteFileRequest(bucketName,objectName, userId));
            return ResponseEntity.ok().body("Delete Success!");
        }catch (NullPointerException e) {
            return ResponseEntity.badRequest().body("UnAuthorized");
        }
    }

    @PostMapping(value = "/addFileCmt", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<String> addFileCmt(@NotNull @AuthenticationPrincipal UserDetail userDetail,
                                             @RequestParam(value = "file") MultipartFile file) throws Exception {
        try {
            String bucketName = "comment";
            UUID idUser = userDetail.getId();
            minIOService.addFile(new UploadFileRequest(file, bucketName, idUser));
            return ResponseEntity.ok().body("Upload avatar success!");
        }catch (NullPointerException e) {
            return ResponseEntity.badRequest().body("UnAuthorized");
        }
    }

    @DeleteMapping("/deleteFileCmt")
    public ResponseEntity<?> deleteFileCmt(@RequestParam(value = "object") String objectName, @NotNull @AuthenticationPrincipal UserDetail userDetail) throws Exception {
        try {
            String bucketName = "comment";
            UUID userId = userDetail.getId();
            minIOService.deleteFile(new DeleteFileRequest(bucketName,objectName, userId));
            return ResponseEntity.ok().body("Delete Success!");
        }catch (NullPointerException e) {
            return ResponseEntity.badRequest().body("UnAuthorized");
        }
    }

//    @DeleteMapping(value = "/deleteListFile")
//    public void deleteListFile(@RequestBody ListRequest listRequest, @RequestParam(value= "bucketName") String bucketName, @AuthenticationPrincipal UserDetail idUser) throws Exception {
//        minIOUntil.deleteListFile(listRequest.getList(), bucketName,idUser.getId());
//    }









}
