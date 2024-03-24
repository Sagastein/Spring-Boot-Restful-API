package com.sage.java.RestfulApi.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "todos")
public class TodoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "Title is required")
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;


}
