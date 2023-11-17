package com.project.applicationsocial.controller;

import com.project.applicationsocial.exception.ForbiddenException;
import com.project.applicationsocial.model.entity.Collections;
import com.project.applicationsocial.payload.response.ResponseModel;
import com.project.applicationsocial.service.FavoritesService;
import com.project.applicationsocial.service.Impl.FavoritesServiceImpl;
import com.project.applicationsocial.service.Impl.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/favorites")
public class FavoritesController {
    @Autowired
    FavoritesService favoritesService;

    @Autowired
    FavoritesServiceImpl favoritesServiceImpl;

    @PostMapping(value = "/add/{postID}/{collectName}")
    public ResponseEntity<?> addFavorites(@AuthenticationPrincipal UserDetail userDetail,
                                          @PathVariable(name = "postID") UUID postID,
                                          @PathVariable(name = "collectName") String collectName)
    {
        if (userDetail == null) {
            throw new ForbiddenException("User is need login before posting!");
        }

        UUID id = userDetail.getId();
        favoritesService.addFavorites(id, postID,collectName);
        ResponseModel responseModel = new ResponseModel<>();
        responseModel.setCode(200);
        responseModel.setData("Added to collection successfully!");
        return ResponseEntity.ok().body(responseModel);
    }


    @DeleteMapping(value = "/delete/{postID}")
    public ResponseEntity<?> removeFavorites(@AuthenticationPrincipal UserDetail userDetail,
                                          @PathVariable(name = "postID") UUID postID)
    {
        if (userDetail == null) {
            throw new ForbiddenException("User is need login before posting!");
        }

        UUID id = userDetail.getId();
        favoritesService.removeFavorites(id, postID);
        ResponseModel responseModel = new ResponseModel<>();
        responseModel.setCode(200);
        responseModel.setData("Deleted to collection successfully!!");
        return ResponseEntity.ok().body(responseModel);
    }


    @GetMapping("/collections")
    public List<Collections> getAll(){
        return favoritesServiceImpl.getAllCollections();
    }
}
