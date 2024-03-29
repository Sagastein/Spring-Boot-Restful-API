package com.sage.java.RestfulApi.Service;

import com.sage.java.RestfulApi.Config.CustomResponse;
import com.sage.java.RestfulApi.Models.TodoModel;
import com.sage.java.RestfulApi.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Override
    public List<TodoModel> getAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public TodoModel createTodo(TodoModel todo) {
        if (todo.getTitle() == null || todo.getTitle().isEmpty()) {
            throw new RuntimeException("Error: Title is required");
        }
        return todoRepository.save(todo);
    }

    @Override
    public ResponseEntity<?> getTodoById(Long id) {
        TodoModel todo = todoRepository.findById(id).orElse(null);
        if (todo == null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Todo not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteTodoById(Long id) {
        TodoModel todo = todoRepository.findById(id).orElse(null);
//        if (todo == null) {
//            Map<String, String> error = new HashMap<>();
//            error.put("error", "Todo not found");
//            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//        }
        if (todo == null) {
            CustomResponse response = new CustomResponse("User not found in the database");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        todoRepository.deleteById(id);
        Map<String, String> message = new HashMap<>();
        message.put("message", "Todo deleted successfully");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateTodoById(Long id, TodoModel todo) {
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
