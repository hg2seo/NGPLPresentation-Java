package com.ngplpresentation.ngpl_backend.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterRequest {

    @NotBlank(message = "사용자 ID는 필수입니다.")
    private String userId;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;

    @NotBlank(message = "확인 비밀번호는 필수입니다.")
    private String confirmPassword;

    @Nullable
    private String name;

    @Email
    @NotBlank(message = "이메일은 필수입니다.")
    private String email;

}
