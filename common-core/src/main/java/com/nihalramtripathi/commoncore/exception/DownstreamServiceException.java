package com.nihalramtripathi.commoncore.exception;

import lombok.Getter;

@Getter
public class DownstreamServiceException
        extends BaseApplicationException {

    private final String correlationId;

    public DownstreamServiceException(
            String code,
            String message,
            int status,
            String correlationId) {

        super(code, message, status);
        this.correlationId = correlationId;
    }
}