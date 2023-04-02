package com.example.dreamcatch.controller;

import com.example.dreamcatch.model.User;
import com.example.dreamcatch.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/get")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("get/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User userToCheck) {
        return userService.login(userToCheck);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<User> editUser(@PathVariable Long id, @RequestBody User userDetails) {
        return userService.edit(id, userDetails);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return userService.delete(id);
    }
}
