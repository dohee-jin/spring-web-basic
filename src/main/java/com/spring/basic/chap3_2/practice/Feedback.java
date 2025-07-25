package com.spring.basic.chap3_2.practice;

import lombok.*;

@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Feedback {

    @Builder.Default
    private Long id = 1L;
    private Long userId;
    private String message;
    private int rating;

}
