package com.maintenecelog.maintenancelog.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ObjectDoesNotExistException extends RuntimeException {
    public ObjectDoesNotExistException(String message) {
        super(message);
    }

    ObjectDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    ObjectDoesNotExistException(Throwable cause) {
        super(cause);
    }
}
