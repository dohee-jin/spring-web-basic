package com.spring.basic.todo.entity;

import lombok.*;

@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class Todo {
    private Long id;
    private String content;
    private boolean completed;
}
