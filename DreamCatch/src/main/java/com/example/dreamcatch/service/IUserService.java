package com.example.dreamcatch.service;

import com.example.dreamcatch.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {
    List<User> getAll();
    ResponseEntity<User> getById(Long id);
    ResponseEntity<User> register(User user);
    ResponseEntity<User> login(User user);
    ResponseEntity<User> edit(Long id, User userDetails);
    ResponseEntity<Void> delete(Long id);
}
