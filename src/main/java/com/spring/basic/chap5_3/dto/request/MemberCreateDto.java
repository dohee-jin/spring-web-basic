package com.spring.basic.chap5_3.dto.request;

// 회원 가입 전용 객체
// DTO

import lombok.*;

@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberCreateDto {

    // 클라이언트 개발자가 원하는 대로 핖드다서
    private String userAcc;
    private String pw;
    private String nick;
}
