package com.spring.basic.todo.controller;

import com.spring.basic.todo.entity.Todo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/practice/todos")
public class TodoController {

    private Long todoId = 1L;
    Map<Long, Todo> todoList = new HashMap<>();

    public TodoController() {
        todoList.put(todoId, new Todo(todoId, "자바 공부하기", false));
        todoId++;
        todoList.put(todoId, new Todo(todoId, "영어 단어 외우기", false));
        todoId++;
        todoList.put(todoId, new Todo(todoId, "일본어 공부하기", true));
        todoId++;
    }

    // todo 리스트 조회하기
    @GetMapping
    public List<Todo> getAllTodo() {
        /*List<Todo> todos = new ArrayList<>();
        for(Todo todo : todoList.values()){
            todos.add(todo);
        }
        return todos;*/
        return new ArrayList<>(todoList.values());
    }

    // todo 추가하기
    @PostMapping
    public Todo addTodo(@RequestParam String content) {
        Todo newTodo = new Todo(todoId, content, false);
        todoList.put(todoId, newTodo);
        todoId++;
        return newTodo;
    }

    // 특정 todo의 상태 완료 토글

}
