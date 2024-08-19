package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Todo;
import com.example.demo.entity.User;
import com.example.demo.repo.TodoRepository;
import com.example.demo.repo.UserRepository;
import com.example.demo.utility.JwtUtil;

@Service
public class TodoService {
    private static final User User = null;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public List<Todo> getTodosForUser(String token) {
        String actualToken = token.startsWith("Bearer ") ? token.substring(7) : token;
        String username = jwtUtil.extractUsername(actualToken);
        return todoRepository.findByUserUsername(username);
    }

        public Todo createTodo(String token, String des, boolean completed) {
        String actualToken = token.startsWith("Bearer ") ? token.substring(7) : token;
        String username = jwtUtil.extractUsername(actualToken);
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (!userOptional.isPresent()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();
        Todo todo = new Todo();
        todo.setDes(des);
        todo.setCompleted(completed);
        todo.setUser(user);

        return todoRepository.save(todo);
}

}
