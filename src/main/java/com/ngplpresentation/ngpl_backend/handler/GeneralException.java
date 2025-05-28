package com.ngplpresentation.ngpl_backend.handler;

import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {
    private final Body body;

    public GeneralException(Status status) {
        super(status.getMessage());
        this.body = status.getBody();
    }

}