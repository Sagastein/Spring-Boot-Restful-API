package com.sage.java.RestfulApi.Service;

import com.sage.java.RestfulApi.Models.UserModel;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> getAllUsers();

    ResponseEntity<?> createUser(UserModel user);

    ResponseEntity<?> getUserById(Long id);

    ResponseEntity<?> deleteUserById();

    ResponseEntity<?> updateUserById();
}
