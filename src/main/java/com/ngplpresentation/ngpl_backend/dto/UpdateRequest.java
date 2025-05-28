package com.ngplpresentation.ngpl_backend.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRequest {

    @NotBlank(message = "사용자 ID는 필수입니다.")
    private String userId;

    @Nullable
    private String name;

    @Nullable
    private String email;

    @Nullable
    private String password;

}
