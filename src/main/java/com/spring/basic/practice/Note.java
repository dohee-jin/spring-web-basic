package com.spring.basic.practice;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Note {

    private Long id;
    private String content;

}
