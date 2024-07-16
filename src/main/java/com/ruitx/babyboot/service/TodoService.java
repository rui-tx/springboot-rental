package com.ruitx.babyboot.service;

import com.ruitx.babyboot.model.Todo;
import com.ruitx.babyboot.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getTodos() {
        List<Todo> todos = new ArrayList<>();
        this.todoRepository.findAll().forEach(todos::add);
        return todos;
    }

    public Todo createTodo(Todo todo) {
        Todo newTodo = new Todo();
        newTodo.setTitle(todo.getTitle());
        newTodo.setDescription(todo.getDescription());
        return this.todoRepository.save(newTodo);
    }
}
