package com.ngplpresentation.ngpl_backend.controller;

import com.ngplpresentation.ngpl_backend.domain.Member;
import com.ngplpresentation.ngpl_backend.dto.DeleteRequest;
import com.ngplpresentation.ngpl_backend.dto.RegisterRequest;
import com.ngplpresentation.ngpl_backend.dto.UpdateRequest;
import com.ngplpresentation.ngpl_backend.service.MemberService;
import com.ngplpresentation.ngpl_backend.dto.response.ApiResponse;
import com.ngplpresentation.ngpl_backend.dto.response.Status;
import jakarta.validation.Valid;
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

    @PostMapping("/register")
    public ApiResponse<?> registerNewMember(@Valid @RequestBody RegisterRequest request) {
        Member member = memberService.registerMember(request);

        return ApiResponse.success(String.valueOf(Status.OK.getCode()),
                Status.OK.getMessage(), member);
    }

    @GetMapping("/account")
    public ApiResponse<?> getMemberInfo(@RequestParam String userId) {
        Member member = memberService.findMember(userId);

        return ApiResponse.success(String.valueOf(Status.OK.getCode()),
                Status.OK.getMessage(), member);
    }

    @PostMapping("/update")
    public ApiResponse<?> updateMember(@Valid @RequestBody UpdateRequest request) {
        Member member = memberService.updateMember(request);

        return ApiResponse.success(String.valueOf(Status.OK.getCode()),
                Status.OK.getMessage(), member);
    }

    @PostMapping("/quit")
    public ApiResponse<?> deleteMember(@Valid @RequestBody DeleteRequest request) {
        memberService.deleteMember(request);

        return ApiResponse.success(String.valueOf(Status.OK.getHttpStatus()),
                Status.OK.getMessage(), null);
    }

}
