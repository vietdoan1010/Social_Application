package com.project.applicationsocial.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteFileRequest {
    String bucketName;
    String objectName;
    UUID idUser;
}
