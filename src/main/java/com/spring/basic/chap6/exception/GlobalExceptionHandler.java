package com.spring.basic.chap6.exception;

import com.spring.basic.chap6.exception.custom.MemberException;
import com.spring.basic.chap6.exception.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

// Api 에서 발생하는 수많은 예외상황들을 도맡아서 처리하는 클래스
@Slf4j
@ControllerAdvice // 컨트롤러 대신 예외처리를 하겠다
public class GlobalExceptionHandler {

    // 예외처리 메소드
    @ExceptionHandler(MemberException.class)
    public ResponseEntity<?> handleClientException(
            MemberException e,
            HttpServletRequest request
            // ,HttpServletResponse response
    ) {
        // 로깅 처리
        log.warn("exception occurred! cased by: {}", e.getMessage());

        // 구체적인 에러 정보 객체를 생성
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(e.getStatus())
                .error("Member is not found")
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();

        // 에러 응답 처리
        return ResponseEntity.status(e.getStatus()).body(errorResponse);
    }
}
