package com.project.applicationsocial.exception;

import com.project.applicationsocial.payload.response.ErrorRespose;
import com.project.applicationsocial.payload.response.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorRespose handlerNotFoundException(NotFoundException ex, WebRequest web){
        return new ErrorRespose(HttpStatus.NOT_FOUND,HttpStatus.NOT_FOUND.value() , ex.getMessage());
    }


    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorRespose handlerUnauthorizeEx(ForbiddenException ex, WebRequest web) {
        return new ErrorRespose(HttpStatus.FORBIDDEN,HttpStatus.FORBIDDEN.value(), ex.getMessage());
    }


    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorRespose handlerBadRequestEx(BadRequestException ex, WebRequest web) {
        return new ErrorRespose(HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }





}
