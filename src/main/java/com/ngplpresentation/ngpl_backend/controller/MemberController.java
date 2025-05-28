package com.ngplpresentation.ngpl_backend.controller;

import com.ngplpresentation.ngpl_backend.service.MemberService;
import com.ngplpresentation.ngpl_backend.response.ApiResponse;
import com.ngplpresentation.ngpl_backend.response.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/test")
    public ApiResponse<?> test() {
        return ApiResponse.success(String.valueOf(Status.OK.getCode()),
            Status.OK.getMessage(), null);
    }

}
