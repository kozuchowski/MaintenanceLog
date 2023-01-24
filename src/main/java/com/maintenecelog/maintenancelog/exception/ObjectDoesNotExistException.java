package com.maintenecelog.maintenancelog.exception;

public class ObjectDoesNotExistException extends RuntimeException{
    public ObjectDoesNotExistException(String message) {
        super(message);
    }

    ObjectDoesNotExistException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
