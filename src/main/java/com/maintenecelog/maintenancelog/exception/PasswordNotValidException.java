package com.maintenecelog.maintenancelog.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PasswordNotValidException extends RuntimeException{

    public PasswordNotValidException(String message) {
        super(message);
    }

    public PasswordNotValidException(Throwable cause) {
        super(cause);
    }

    public PasswordNotValidException(String message, Throwable cause) {
        super(message, cause);
    }
}
