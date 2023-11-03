package com.springbootlearn.todo.Service;

import java.util.List;

import com.springbootlearn.todo.dto.TodoDto;

public interface TodoService {

    TodoDto saveTodo(TodoDto todoDto);

    TodoDto getTodo(Long id);

    List<TodoDto> getAllTodo();

    TodoDto updateTodo(TodoDto todoDto, Long Id);

    void deleteTodo(Long id);

    TodoDto completeTodo(Long id);

    TodoDto inCompleteTodo(Long id);
    
}
