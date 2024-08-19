package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Todo;
 
public interface TodoRepository extends JpaRepository<Todo,Long>{

    List<Todo> findByUserUsername(String username);
    
}
