package com.ngplpresentation.ngpl_backend.controller;

import com.ngplpresentation.ngpl_backend.dto.PasswordValidateRequest;
import com.ngplpresentation.ngpl_backend.handler.GeneralException;
import com.ngplpresentation.ngpl_backend.service.MemberService;
import com.ngplpresentation.ngpl_backend.dto.response.ApiResponse;
import com.ngplpresentation.ngpl_backend.dto.response.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/test")
    public ApiResponse<?> test() {
        return ApiResponse.success(String.valueOf(Status.OK.getCode()),
            Status.OK.getMessage(), null);
    }

    @PostMapping("/validate_password")
    public ApiResponse<?> checkPasswordValidation(@RequestBody PasswordValidateRequest req) {

        if (req == null)
            throw new GeneralException(Status.BAD_REQUEST);

        Boolean isValidate = memberService.validatePassword(req);

        if (isValidate)
            return ApiResponse.success(String.valueOf(Status.OK.getCode()),
                    Status.OK.getMessage(), null);
        else
            return ApiResponse.onFailure(String.valueOf(Status.BAD_REQUEST.getCode()),
                    "검증을 요청한 비밀번호가 유효하지 않습니다.", null);
    }
}
