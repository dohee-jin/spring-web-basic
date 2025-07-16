package com.spring.basic.score.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/scores")
public class ViewController {

    // 화면 라우팅
    @GetMapping("/page")
    public String showPage() {
        return "/score/score-page";
    }

}
