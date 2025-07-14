package com.spring.basic.chap2_3.practice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/practice/api/v1")
public class PracticeController2_3 {

    @GetMapping("/welcome")
    public String Welcome() {
        return "Welcome to Spring MVC";
    }

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable("id") String productId) {
        return "Product ID: " + productId;
    }


}
