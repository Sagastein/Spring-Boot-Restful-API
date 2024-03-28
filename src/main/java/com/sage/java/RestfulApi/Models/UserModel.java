package com.sage.java.RestfulApi.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@DynamicUpdate
@Setter
@Entity
@Table(name = "users")
public class UserModel {
    //create user model with name email password and age
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String email;
    private String password;
    private int age;

}