package com.project.applicationsocial.controller;

import com.project.applicationsocial.exception.ForbiddenException;
import com.project.applicationsocial.model.entity.Collections;
import com.project.applicationsocial.model.entity.Users;
import com.project.applicationsocial.payload.response.ResponseModel;
import com.project.applicationsocial.service.CollectionsService;
import com.project.applicationsocial.service.Impl.UserDetail;
import okhttp3.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/collections")
public class CollectionsController {

    @Autowired
    CollectionsService collectionsService;

    @GetMapping("/all")
    public List<Collections> getAllCollections(@AuthenticationPrincipal UserDetail userDetail) {
        if (userDetail == null) {
            throw new ForbiddenException("User is need login before posting!");
        }
        return  collectionsService.getAllCollections(userDetail.getId());
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCollectionName(@AuthenticationPrincipal UserDetail userDetail,
                                                  @RequestParam(name = "collectionName") String collectionName,
                                                  @RequestParam(name = "newNameCollection") String newNameCollection)
    {
        if (userDetail == null) {
            throw new ForbiddenException("User is need login before update name collection!");
        }

        UUID id = userDetail.getId();
        collectionsService.updateCollectionName(userDetail.getId(),collectionName, newNameCollection);
        ResponseModel responseModel = new ResponseModel<>();
        responseModel.setCode(200);
        responseModel.setData("Update name to collection successfully!!");
        return ResponseEntity.ok().body(responseModel);
    }
}
