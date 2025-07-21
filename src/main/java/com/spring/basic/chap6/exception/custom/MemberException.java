package com.spring.basic.chap6.exception.custom;

import com.spring.basic.chap6.exception.dto.ErrorCode;
import lombok.Getter;

// 사용자 정의 예외클래스 - 개발자가 원하는 스펙에 맞는 에러 클래스
// 에러 클래스를 상속 받아야 에러 클래스로 인식함
@Getter
public class MemberException extends RuntimeException{

    private final int status; // 에러 상태코드
    private final String errorName; // 에러 이름

    public MemberException(ErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorName = errorCode.toString();
        this.status = errorCode.getStatusCode();
    }
}
