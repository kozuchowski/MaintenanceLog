package com.maintenecelog.maintenancelog.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ObjectAlreadyExistsException extends RuntimeException {
    public ObjectAlreadyExistsException(String message){
        super(message);
    }
    public ObjectAlreadyExistsException(String message, Throwable cause){
        super(message, cause);
    }
}
