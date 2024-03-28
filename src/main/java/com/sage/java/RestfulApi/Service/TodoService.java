package com.sage.java.RestfulApi.Service;

import com.sage.java.RestfulApi.Models.TodoModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TodoService {
    List<TodoModel> getAllTodos();

    TodoModel createTodo(TodoModel todo);

    ResponseEntity<?> getTodoById(Long id);

    ResponseEntity<?> deleteTodoById(Long id);

    ResponseEntity<?> updateTodoById(Long id, TodoModel todo);
}
