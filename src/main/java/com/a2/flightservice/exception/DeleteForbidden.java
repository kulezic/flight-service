package com.a2.flightservice.exception;

import org.springframework.http.HttpStatus;

public class DeleteForbidden extends CustomException{
    public DeleteForbidden(String message) {
        super(message, ErrorCode.FORBIDDEN, HttpStatus.FORBIDDEN);
    }
}
