package com.project.applicationsocial.payload.request;

import com.project.applicationsocial.model.StatusEnum;
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
    private StatusEnum status;
    private MultipartFile[] files;
}
