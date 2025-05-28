package com.ngplpresentation.ngpl_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PasswordValidateRequest {
    private String password;
    private String confirmPassword;
}
