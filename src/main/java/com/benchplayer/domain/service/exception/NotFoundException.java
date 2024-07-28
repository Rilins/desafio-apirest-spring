package com.benchplayer.domain.service.exception;

public class NotFoundException extends ExceptionGlobal {

    public NotFoundException() {
        super("Resource not found.");
    }
    
}
