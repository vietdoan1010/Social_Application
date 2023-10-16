package com.project.applicationsocial.payload.repose;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadReponse {
    String urlHttp;
    String urlPath;
}
