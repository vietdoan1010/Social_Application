package com.project.applicationsocial.controller;
import com.project.applicationsocial.exception.ForbiddenException;
import com.project.applicationsocial.payload.response.ResponseModel;
import com.project.applicationsocial.payload.request.DeleteFileRequest;
import com.project.applicationsocial.payload.request.UploadFileRequest;
import com.project.applicationsocial.service.Impl.FileServiceImpl;
import com.project.applicationsocial.service.Impl.UserDetail;
import com.project.applicationsocial.service.until.MinIOUntil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<ResponseModel> addFileAvt(@AuthenticationPrincipal UserDetail userDetail,
                                        @RequestParam(value = "file") MultipartFile file ) throws Exception {

        ResponseModel<String> responseModel = new ResponseModel<>();
        if (userDetail == null)    {
            throw new ForbiddenException("User is need login before upload file");
        }
        String bucketName = "avatar";
        UUID idUser = userDetail.getId();
        minIOService.addFile(new UploadFileRequest(file, bucketName, idUser));
        responseModel.setData("Upload file success!");
        responseModel.setCode(200);
        responseModel.setError(null);
        return ResponseEntity.ok().body(responseModel);

    }

    @DeleteMapping("/deleteFileAvt")
    public ResponseEntity<ResponseModel> deleteFileAvt(@RequestParam(value = "object") String objectName, @AuthenticationPrincipal UserDetail userDetail) throws Exception {
        if (userDetail == null)    {
            throw new ForbiddenException("User is need login before delete file");
        }
        ResponseModel<String> responseModel = new ResponseModel<>();

        String bucketName = "avatar";
        UUID idUser = userDetail.getId();
        minIOService.deleteFile(new DeleteFileRequest(bucketName,objectName, idUser));
        responseModel.setData("Delete file success!");
        responseModel.setCode(200);
        responseModel.setError(null);
        return ResponseEntity.ok().body(responseModel);
    }

    @PostMapping(value = "/addFilePost", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<ResponseModel> addFilePost( @AuthenticationPrincipal UserDetail userDetail,
                                              @RequestParam(value = "file") MultipartFile file) throws Exception {
        ResponseModel<String> responseModel = new ResponseModel<>();
        if (userDetail == null)    {
            throw new ForbiddenException("User is need login before upload file");
        }
        String bucketName = "post";
        UUID idUser = userDetail.getId();
        minIOService.addFile(new UploadFileRequest(file, bucketName, idUser));
        responseModel.setData("Upload file success!");
        responseModel.setCode(200);
        responseModel.setError(null);
        return ResponseEntity.ok().body(responseModel);
    }

    @DeleteMapping("/deleteFilePost")
    public ResponseEntity<?> deleteFilePost(@RequestParam(value = "object") String objectName, @AuthenticationPrincipal UserDetail userDetail) throws Exception {
        if (userDetail == null)    {
            throw new ForbiddenException("User is need login before delete file");
        }
        ResponseModel<String> responseModel = new ResponseModel<>();

        String bucketName = "post";
        UUID idUser = userDetail.getId();
        minIOService.deleteFile(new DeleteFileRequest(bucketName,objectName, idUser));
        responseModel.setData("Delete file success!");
        responseModel.setCode(200);
        responseModel.setError(null);
        return ResponseEntity.ok().body(responseModel);
    }

    @PostMapping(value = "/addFileCmt", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<ResponseModel> addFileCmt( @AuthenticationPrincipal UserDetail userDetail,
                                             @RequestParam(value = "file") MultipartFile file) throws Exception {
        ResponseModel<String> responseModel = new ResponseModel<>();
        if (userDetail == null)    {
            throw new ForbiddenException("User is need login before upload file");
        }
        String bucketName = "comment";
        UUID idUser = userDetail.getId();
        minIOService.addFile(new UploadFileRequest(file, bucketName, idUser));
        responseModel.setData("Upload file success!");
        responseModel.setCode(200);
        responseModel.setError(null);
        return ResponseEntity.ok().body(responseModel);
    }

    @DeleteMapping("/deleteFileCmt")
    public ResponseEntity<?> deleteFileCmt(@RequestParam(value = "object") String objectName,  @AuthenticationPrincipal UserDetail userDetail) throws Exception {
        if (userDetail == null)    {
            throw new ForbiddenException("User is need login before delete file");
        }
        ResponseModel<String> responseModel = new ResponseModel<>();

        String bucketName = "comment";
        UUID idUser = userDetail.getId();
        minIOService.deleteFile(new DeleteFileRequest(bucketName,objectName, idUser));
        responseModel.setData("Delete file success!");
        responseModel.setCode(200);
        responseModel.setError(null);
        return ResponseEntity.ok().body(responseModel);
    }











}
