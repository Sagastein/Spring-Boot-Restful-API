package com.sage.java.RestfulApi.Controller;

import com.sage.java.RestfulApi.Models.TodoModel;
import com.sage.java.RestfulApi.Service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<TodoModel> getAllTodos() {
        return todoService.getAllTodos();
    }

    @PostMapping
    public ResponseEntity<TodoModel> createTodo(@RequestBody TodoModel todo) {
        TodoModel savedTodo = todoService.createTodo(todo);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTodoById(@PathVariable Long id) {
        return todoService.getTodoById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodoById(@PathVariable Long id) {
        return todoService.deleteTodoById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTodoById(@PathVariable Long id, @RequestBody TodoModel todo) {
        return todoService.updateTodoById(id, todo);
    }
}




