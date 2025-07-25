package com.spring.basic.chap1_3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

// DispatcherServlet이 이 객체를 하위 컨트롤러로 인식
@Controller
public class HelloController {

    @RequestMapping("/chap01/hello")
    // 이 어노테이션이 있으면 json을 리턴하려고 시도
    // 없으면 view를 찾음
    @ResponseBody
    public Map<String, Object> hello() {
        System.out.println("Hello Spring MVC");
        return Map.of(
                "name", "김철수",
                "age", 50
        );
    }

    // 컨트롤러 기능 - 요청을 처리, 뷰를 선택해서 포워딩 기능
    @RequestMapping("/chap01/bye")
    public String bye() {
        System.out.println("잘가게");

        // bye.jsp를 열어주고 싶음 (뷰 포워딩)
        // return "/WEB-INF/views/bye.js";
        return "bye";
    }

}
