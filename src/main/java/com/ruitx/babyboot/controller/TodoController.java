package com.ruitx.babyboot.controller;

import com.ruitx.babyboot.model.Todo;
import com.ruitx.babyboot.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping(path = {"","/"})
    public List<Todo> getTodos() {
        return this.todoService.getTodos();
    }

    @PostMapping(path = {"","/"})
    public Todo createTodo(@RequestBody Todo todo) {
        return this.todoService.createTodo(todo);
    }

}
