package com.spring.basic.chap3_2.practice;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v3-2/practice/feedbacks")
public class Controller {

    private Map<Long, Feedback> feedbackStore = new HashMap<>();

    // 피드백 조회
    @GetMapping
    public List<Feedback> list() {
        return new ArrayList<>(feedbackStore.values());
    }

    // 피드백 전송
    @PostMapping
    public String createFeedback(@RequestBody Feedback feedback) {
        feedback.setId((long) (Math.random() * 10000 + 1));
        feedbackStore.put(feedback.getId(), feedback);
        return "Feedback created: " + feedback;
    }

}
