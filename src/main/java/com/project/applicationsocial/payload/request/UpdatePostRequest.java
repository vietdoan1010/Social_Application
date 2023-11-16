package com.project.applicationsocial.payload.request;

import com.project.applicationsocial.model.ENUM.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePostRequest {
    private String title;
    private String body;
    private StatusEnum status;
    private MultipartFile[] addFile;
    private List<String> removeFile;
}
