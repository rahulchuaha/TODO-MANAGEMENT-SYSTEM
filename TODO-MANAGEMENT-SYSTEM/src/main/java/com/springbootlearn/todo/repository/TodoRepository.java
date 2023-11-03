package com.springbootlearn.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootlearn.todo.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>{
    
}
