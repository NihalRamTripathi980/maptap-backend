package com.nihalramtripathi.commoncore.exception;

import lombok.Getter;

@Getter
public class BaseApplicationException extends RuntimeException {

    private final String code;
    private final int status;

    public BaseApplicationException(
            String code,
            String message,
            int status) {

        super(message);
        this.code = code;
        this.status = status;
    }
}