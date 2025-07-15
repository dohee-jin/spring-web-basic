package com.spring.basic.chap3_2.controller;

import com.spring.basic.chap3_2.entity.Member;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v3-2/members")
public class MemberController3_2 {

    private Map<String, Member> memberStore = new HashMap<>();

    public MemberController3_2() {
        Member member1 = Member.builder()
                .account("abc123")
                .password("1125")
                .nickname("타롯치")
                .build();

        Member member2 = Member.builder()
                .account("def123")
                .password("0913")
                .nickname("숑차니")
                .build();

        memberStore.put(member1.getUid(), member1);
        memberStore.put(member2.getUid(), member2);

    }

    // 전체조회 - get
    @GetMapping
    public List<Member> list() {
        return new ArrayList<>(memberStore.values());
    }

    // 회원등록 - post
    // ?account=xxxx&password=xxx..... -> 이 방법은 불편하다.
    // 전송할 데이터를 JSON 객체로 묶어서 보내주셈,,, 풀어서 쓸게
    /*
        @RequestBody
        Json -> JAVA 객체
        {
            "account": "XXX",
            "password": "xxx",
            "nickname": "zzz"
        }
     */
    @PostMapping
    public String join(@RequestBody Member member) {
        member.setUid(UUID.randomUUID().toString());
        memberStore.put(member.getUid(), member);
        return "새로운 멤버가 생성됨! - nickname: " + member.getNickname();
    }
}
