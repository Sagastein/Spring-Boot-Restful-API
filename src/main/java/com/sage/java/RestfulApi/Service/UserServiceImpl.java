package com.sage.java.RestfulApi.Service;

import com.sage.java.RestfulApi.Models.UserModel;
import com.sage.java.RestfulApi.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override

    public ResponseEntity<?> getAllUsers() {
        try {
            List<UserModel> users = userRepository.findAll();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtils.createErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public ResponseEntity<?> createUser(@RequestBody UserModel user) {
        try {
            //check if body in not empty
            if (user == null) {
                throw new RuntimeException("Error: User is required");
            }

            if (user.getName() == null || user.getName().isEmpty()) {
                throw new RuntimeException("Error: Name is required");
            }
            //check if user exist by email
            UserModel userByEmail = userRepository.findByEmail(user.getEmail());
            if (userByEmail != null) {
                throw new RuntimeException("Error: User with email " + user.getEmail() + " already exists");
            }

            userRepository.save(user);
            return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseUtils.createErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return ResponseUtils.createErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getUserById(Long id) {
        try {
            UserModel user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseUtils.createErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> deleteUserById(Long id) {
        try {
            UserModel user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
            userRepository.delete(user);
            return ResponseUtils.createErrorResponse("User deleted successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseUtils.createErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> updateUserById(Long id, @RequestBody UserModel updatedUser) {
        try {
            UserModel existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

            // Update the existingUser with new data from updatedUser
            // For example:
            if (updatedUser.getName() != null) {
                existingUser.setName(updatedUser.getName());
            }
            if (updatedUser.getEmail() != null) {
                existingUser.setEmail(updatedUser.getEmail());
            }
            if (updatedUser.getPassword() != null) {
                existingUser.setPassword(updatedUser.getPassword());
            }
            if (updatedUser.getAge() != 0) {
                existingUser.setAge(updatedUser.getAge());
            }

            userRepository.save(existingUser);
            return ResponseEntity.ok("User updated successfully");
        } catch (RuntimeException e) {
            return ResponseUtils.createErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ResponseUtils.createErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

