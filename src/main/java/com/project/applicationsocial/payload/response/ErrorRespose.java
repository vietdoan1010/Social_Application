package com.project.applicationsocial.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorRespose {
    private HttpStatus error;
    private int code;
    private  String message;

}
