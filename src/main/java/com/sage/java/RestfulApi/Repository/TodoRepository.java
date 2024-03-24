package com.sage.java.RestfulApi.Repository;

import com.sage.java.RestfulApi.Models.TodoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoModel, Long> {
}

