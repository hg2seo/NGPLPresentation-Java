package com.ngplpresentation.ngpl_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum Status {
    OK(200, "요청이 성공적으로 처리되었습니다.", HttpStatus.OK),

    BAD_REQUEST(400, "요청의 Request Body가 필요합니다.", HttpStatus.BAD_REQUEST),
    BAD_REQUEST_ARGUMENT(400, "요청 내용이 잘못되었습니다.", HttpStatus.BAD_REQUEST),

    INTERNAL_SERVER_ERROR(500, "서버 오류", HttpStatus.INTERNAL_SERVER_ERROR);

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    public Body getBody() {
        return new Body(code, message, httpStatus);
    }
}
