package com.project.applicationsocial.exception;

import com.project.applicationsocial.model.DTO.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<UserDTO> NotFoundException(Exception e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
