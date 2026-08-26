package com.nihalramtripathi.commoncore.exception;

public class ResourceNotFoundException
        extends BaseApplicationException {

    public ResourceNotFoundException(
            String code,
            String message) {

        super(code, message, 404);
    }
}