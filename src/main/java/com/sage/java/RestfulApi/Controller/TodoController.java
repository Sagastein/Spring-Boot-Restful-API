package com.sage.java.RestfulApi.Controller;

import com.sage.java.RestfulApi.Models.TodoModel;
import com.sage.java.RestfulApi.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping
    public List<TodoModel> getAllTodos() {
        return todoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<TodoModel> createTodo(@RequestBody TodoModel todo) {
        if (todo.getTitle() == null || todo.getTitle().isEmpty()) {
            System.out.println("Error: Title is required");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        System.out.println("Creating todo: " + todo);
        TodoModel savedTodo = todoRepository.save(todo);
        System.out.println("Saved todo: " + savedTodo);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }


    //get todo by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getTodoById(@PathVariable Long id) {
        TodoModel todo = todoRepository.findById(id).orElse(null);
        if (todo == null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Todo not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }


    // Add other CRUD methods as needed (e.g., getTodoById, updateTodo, deleteTodo)
    //delete todo by id

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodoById(@PathVariable Long id) {
        TodoModel todo = todoRepository.findById(id).orElse(null);
        if (todo == null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Todo not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        todoRepository.deleteById(id);
        Map<String, String> message = new HashMap<>();
        message.put("message", "Todo updated successfully");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //update user by id
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTodoById(@PathVariable Long id, @RequestBody TodoModel todo) {
        TodoModel todoToUpdate = todoRepository.findById(id).orElse(null);
        if (todoToUpdate == null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Todo not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        todoToUpdate.setTitle(todo.getTitle());
        todoToUpdate.setDescription(todo.getDescription());
        TodoModel updatedTodo = todoRepository.save(todoToUpdate);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }
}




