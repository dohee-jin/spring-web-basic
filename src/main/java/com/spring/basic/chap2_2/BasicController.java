package com.spring.basic.chap2_2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller - Jsp 같은 뷰를 핸들링하는 컨트롤러
//@Controller
// RestController - JSON을 리턴하는 컨트롤러 (Controller + ResponseBody)
@RestController
@RequestMapping("/chap2-2")
public class BasicController {

    /*
        Http 요청 방식
        GET : 조회(Create)
        POST : 생성(Read)
        PUT : 수정(Update)
        DELETE : 삭제(Delete)
     */


    // 핸들러 메소드 (각 요청을 전담처리)
    // @RequestMapping(value = "/chap2-2/hello", method = RequestMethod.GET)
    @GetMapping("/hello")
    // @ResponseBody // JSON을 리턴
    public String hello() {
        return "Hello Spring";
        // Controller로 붙였을 경우 Hello Spring.jsp 파일을 찾아서 리턴
    }

    @PostMapping("/hobby")
    public List<String> greet() {
        return List.of("수영", "축구", "낮잠", "시비걸기");
    }
}
