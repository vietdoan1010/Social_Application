package com.project.applicationsocial.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    private String title;

    private String body;

    private MultipartFile[] files;
}
