package com.spring.basic.chap2_5;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/v2-5")
public class ResponseController2_5 {

    // 페이지 라우팅 - 특정 뷰(JSP, thymeleaf)를 포워딩 해주는 것
    @GetMapping("/book-page")
    public String bookPage() {
        return "book-page";
    }

    // 클라이언트에게 데이터 응답
    // html 응답
    @GetMapping("/show/html")
    @ResponseBody // 순수 데이터를 클라이언트에게 전달
    public String html() {
        return """
                <html>
                <body>
                    <h1>HTML 응답하기</h1>
                </body>
                </html>
                """;
    }

    // 순수 텍스트 응답하기, value="", produces=""
    @GetMapping(value = "/show/text", produces ="text/plain")
    @ResponseBody // 2가지: Json 이거나 html
    public String text() {
        return "하이 난 문자야~~";
    }

    // 순수 텍스트도 json 으로 응답
    @GetMapping(value = "show/text2")
    @ResponseBody
    public Map<String, Object> text2() {
        return Map.of(
                "message", "하이 난 문자야~~~"
        );
    }

    // Json 배열 응답
    @GetMapping("/json/array")
    @ResponseBody
    public List<String> hobbies() {
        return List.of("테니스", "수학문제풀기", "탁구");
    }

    // json 객체 응답 - 자바의 클래스의 인스턴스(객체) or Map
    @GetMapping("/json/object")
    @ResponseBody
    public Map<String, Object> myPet() {
        return Map.of(
                "name", "야옹이",
                "age", 3,
                "kind", "코리안숏헤어"
        );
    }

    @GetMapping("/json/object2")
    @ResponseBody
    public Pet myPet2() {
        return new Pet("타로쨩", 5, "수달", true);
    }

    @GetMapping("/json/object3")
    @ResponseBody
    public List<Pet> myPet3() {
        return List.of(
                new Pet("숑뭉이", 4, "리트리버", true),
                new Pet("또리", 2, "오리", true)
        );
    }

    @ToString
    @Getter
    @AllArgsConstructor
    private static class Pet {
        private String name;
        private int age;
        private String kind;
        private boolean injection;
    }

}
