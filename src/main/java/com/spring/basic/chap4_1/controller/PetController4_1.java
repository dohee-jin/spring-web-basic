package com.spring.basic.chap4_1.controller;

import com.spring.basic.chap4_1.entity.Pet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller // Jsp 작업할 때는 그냥 controller 사용
@RequestMapping("/chap4-1")
public class PetController4_1 {

    private static long nextNo = 1L;
    private List<Pet> petList = new ArrayList<>();

    public PetController4_1() {
        petList.add(Pet.builder()
                .id(nextNo++)
                .age(3)
                .name("뽀삐")
                .kind("불독")
                .injection(true)
                .build());

        petList.add(Pet.builder()
                .id(nextNo++)
                .age(4)
                .name("초코")
                .kind("도베르만")
                .injection(false)
                .build());

        petList.add(Pet.builder()
                .id(nextNo++)
                .age(1)
                .name("나비")
                .kind("벵갈호랑이")
                .injection(true)
                .build());
    }

    // per.jsp를 열어줘(뷰 포워딩)
    @GetMapping("/pet-page")
    public String petPage(Model model) {
        // 서버사이드 랜더링을 위해 JSP 파일에게 리스트를 전달
        model.addAttribute("petList", petList);
        return "chap4-1/pet";
    }
}
