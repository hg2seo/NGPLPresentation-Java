package com.ngplpresentation.ngpl_backend.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class Body {
    private final int code;
    private final String message;
    private final HttpStatus httpStatus;
}
