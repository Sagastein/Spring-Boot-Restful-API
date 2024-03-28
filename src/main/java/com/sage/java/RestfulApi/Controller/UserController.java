package com.sage.java.RestfulApi.Controller;

import com.sage.java.RestfulApi.Models.UserModel;
import com.sage.java.RestfulApi.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserModel user) {
        return userService.createUser(user);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        return userService.deleteUserById(id);
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> updateUserById(@PathVariable Long id, @RequestBody UserModel user) {
        return userService.updateUserById(id, user);
    }

}
