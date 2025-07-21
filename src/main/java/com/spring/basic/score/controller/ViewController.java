package com.spring.basic.score.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/score")
public class ViewController {

    // 화면 라우팅
    @GetMapping
    public String showPage() {
        return "/score/score-page";
    }

    // 디테일 화면 라우팅
    @GetMapping("/{id}")
    public String DetailPage(@PathVariable("id") Long id) {
        return "/score/score-detail";
    }

}
