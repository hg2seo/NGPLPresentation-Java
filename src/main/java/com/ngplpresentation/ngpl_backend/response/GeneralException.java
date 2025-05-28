package com.ngplpresentation.ngpl_backend.response;

import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {
    private final Body body;

    public GeneralException(Status status) {
        super(status.getMessage());
        this.body = status.getBody();
    }

}