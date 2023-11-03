package com.springbootlearn.todo.Service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.springbootlearn.todo.Service.TodoService;
import com.springbootlearn.todo.dto.TodoDto;
import com.springbootlearn.todo.entity.Todo;
import com.springbootlearn.todo.exception.ResourceNotFoundException;
import com.springbootlearn.todo.repository.TodoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService{

    private TodoRepository todoRepository;

    private ModelMapper modelMapper;

    @Override
    public TodoDto saveTodo(TodoDto todoDto) {
        Todo todo = modelMapper.map(todoDto, Todo.class);

        Todo savedTodo = todoRepository.save(todo);

        TodoDto savedTodoDto = modelMapper.map(savedTodo, TodoDto.class);
        return savedTodoDto;
    }

    @Override
    public TodoDto getTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found with id"+id));
        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodo() {
        List<Todo> todos = todoRepository.findAll();
        return todos.stream().map((todo) -> modelMapper.map(todo, TodoDto.class)).collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long todoId) {
       Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new ResourceNotFoundException("user not found with id"+todoId));

       todo.setTitle(todoDto.getTitle());
       todo.setDescription(todoDto.getDescription());
       todo.setCompleted(todoDto.isCompleted());

       Todo updateTodo = todoRepository.save(todo);

        return modelMapper.map(updateTodo, TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
       Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found with id"+id));
       todoRepository.deleteById(id);
        
    }

    @Override
    public TodoDto completeTodo(Long id) {
       Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found with id"+id));
       
              todo.setCompleted(Boolean.TRUE);
              Todo updatedTodo = todoRepository.save(todo);
               return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public TodoDto inCompleteTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found with id" + id));
        todo.setCompleted(Boolean.FALSE);
        Todo updateTodo = todoRepository.save(todo);
        return modelMapper.map(updateTodo, TodoDto.class);
    }

    
}
