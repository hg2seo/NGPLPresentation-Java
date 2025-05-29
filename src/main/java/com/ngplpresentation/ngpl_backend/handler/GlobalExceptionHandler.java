package com.ngplpresentation.ngpl_backend.handler;

import com.ngplpresentation.ngpl_backend.dto.response.ApiResponse;
import com.ngplpresentation.ngpl_backend.dto.response.Body;
import com.ngplpresentation.ngpl_backend.dto.response.Status;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        ApiResponse<Object> response = ApiResponse.onFailure(
                String.valueOf(Status.BAD_REQUEST_ARGUMENT.getCode()),
                Status.BAD_REQUEST_ARGUMENT.getMessage(),
                ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .collect(Collectors.groupingBy(
                                FieldError::getField,
                                Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())
                        ))
        );

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<Object> generalException(
            GeneralException generalException,
            HttpServletRequest request
    ) {
        Body body = generalException.getBody();
        ApiResponse<Object> response = ApiResponse.onFailure(String.valueOf(body.getCode()), body.getMessage(), null);
        WebRequest webRequest = new ServletWebRequest(request);
        return super.handleExceptionInternal(
                generalException,
                response,
                HttpHeaders.EMPTY,
                body.getHttpStatus(),
                webRequest
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> unexpectedException(
        Exception unexpectedException,
        WebRequest webRequest
    ) {
        log.error("예상치 못한 오류 발생: {}", unexpectedException.getMessage());
        log.error("발생 지점: {}", unexpectedException.getStackTrace()[0]);
        Body body = Status.INTERNAL_SERVER_ERROR.getBody();
        ApiResponse<Object> response = ApiResponse.onFailure(String.valueOf(body.getCode()), body.getMessage(), null);
        return super.handleExceptionInternal(
            unexpectedException,
            response,
            HttpHeaders.EMPTY,
            HttpStatus.INTERNAL_SERVER_ERROR,
            webRequest
        );
    }
}