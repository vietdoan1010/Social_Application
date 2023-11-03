package com.project.applicationsocial.exception;

public class BadRequestException extends  RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}
