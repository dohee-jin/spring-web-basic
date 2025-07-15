package com.spring.basic.chap4_2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/chap4-2")
public class ThymeLeafController {

    @GetMapping("/hobby-page")
    public String hobbyPage(Model model) {
        model.addAttribute("username", "또리");
        model.addAttribute("hobbies", List.of("동생괴롭히기", "폭식하기"));
        return "hobby";
    }
}
