package com.nihalramtripathi.commoncore.exception;

public class BadRequestException
        extends BaseApplicationException {

    public BadRequestException(
            String code,
            String message) {

        super(code, message, 400);
    }
}