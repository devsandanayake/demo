package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TodoDTO;
import com.example.demo.entity.Todo;
import com.example.demo.service.TodoService;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping
    public ResponseEntity<List<Todo>> getTodosForUser(@RequestHeader("Authorization") String token){
        List<Todo> todos = todoService.getTodosForUser(token);
        return ResponseEntity.ok(todos);
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestHeader("Authorization") String token, @RequestBody TodoDTO todoDTO) {
        Todo todo = todoService.createTodo(token, todoDTO.getDes(), todoDTO.isCompleted());
        return ResponseEntity.ok(todo);
    }
}