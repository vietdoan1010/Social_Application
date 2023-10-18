package com.project.applicationsocial.payload.repose;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadReponse {
    String urlHttp;
}
