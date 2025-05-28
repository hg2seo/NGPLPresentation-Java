package com.ngplpresentation.ngpl_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum Status {
    OK(200, "요청이 성공적으로 처리되었습니다.", HttpStatus.OK),

    BAD_REQUEST(400, "잘못된 요청입니다.", HttpStatus.BAD_REQUEST),
    BAD_REQUEST_ARGUMENT_PWD_MIN_MAX(400, "비밀번호는 8자 이상, 16자 이하의 문자열이어야 합니다.", HttpStatus.BAD_REQUEST),
    BAD_REQUEST_ARGUMENT_PWD_NOT_SAME(400, "비밀번호와 확인 비밀번호가 같지 않습니다.", HttpStatus.BAD_REQUEST),
    BAD_REQUEST_EXISTING_USERID(400, "이미 존재하는 사용자 ID 입니다.", HttpStatus.BAD_REQUEST),
    BAD_REQUEST_EXISTING_EMAIL(400, "이미 존재하는 이메일입니다.", HttpStatus.BAD_REQUEST),
    BAD_REQUEST_NON_EXISTENCE_MEMBER(400, "존재하지 않는 회원입니다.", HttpStatus.BAD_REQUEST),

    INTERNAL_SERVER_ERROR(500, "서버 오류", HttpStatus.INTERNAL_SERVER_ERROR);

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    public Body getBody() {
        return new Body(code, message, httpStatus);
    }
}
