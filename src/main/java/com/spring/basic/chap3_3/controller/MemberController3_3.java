package com.spring.basic.chap3_3.controller;

import com.spring.basic.chap3_2.entity.Member;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v3-3/members")
// @CrossOrigin("http://127.0.0.1:5500") - 간편설정
public class MemberController3_3 {

    private Map<String, Member> memberStore = new HashMap<>();

    public MemberController3_3() {
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
    public ResponseEntity<?> list() {

        /*try {

            String str = null;
            str.charAt(0);

        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body("서버측 에러임 어쩔");
        }*/

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("my-pet", "cat"); // 맵 or 리스트 처럼 사용 가능
        httpHeaders.add("hobby", "swim");

        return ResponseEntity
                .ok() // 상태정보
                .headers(httpHeaders) // 헤더
                .body(new ArrayList<>(memberStore.values())); // 바디
    }

    // 단일 조회 - 계정명으로 조회, 없으면 404
    @GetMapping("/{account}")
    public ResponseEntity<?> singleUserList(@PathVariable String account) {

        Member foundMember = memberStore.values()
                .stream()
                .filter(member -> member.getAccount().equals(account))
                .findFirst()
                .orElse(null);

        if(foundMember == null) {
            return ResponseEntity
                    .status(404)
                    .body(account + "는(은) 존재하지 않습니다.");
        }

        return ResponseEntity
                .ok()
                .body(foundMember);

        /*
        // 내코드
        for(Member member : memberStore.values()) {
            // 계정 O
            if(member.getAccount().equalsIgnoreCase(account)){

                Map<String, String> user = new HashMap<>();

                user.put("nickname", member.getNickname());
                user.put("account", member.getAccount());

                return ResponseEntity
                        .ok()
                        .body(user);
            }
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND) // notFound 는 Body 사용 X
                .body("사용자가 존재하지 않습니다");


         */
    }

    // 회원등록 - post
    @PostMapping
    // 모든 Restcontroller 의 리턴타입은 ResponseEntity<타입>, <?> - 귀찮으면, <타입> - 원래는 구체적으로 명시해줘야 함
    public ResponseEntity<String> join(@RequestBody Member member) {

        // 계정이 비어있는 지 확인
        if(member.getAccount().isBlank()) {
            return ResponseEntity
                    .badRequest() // 404 - notFound, 435? 커스텀 번호 - status(435)
                    .body("계정은 필수 값 입니다.");
        }

        member.setUid(UUID.randomUUID().toString());
        memberStore.put(member.getUid(), member);
        return ResponseEntity
                .ok() // 200
                .body("새로운 멤버가 생성됨! - nickname: " + member.getNickname());
    }
}