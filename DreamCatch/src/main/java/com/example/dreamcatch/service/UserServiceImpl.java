package com.example.dreamcatch.service;

import com.example.dreamcatch.model.User;
import com.example.dreamcatch.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public ResponseEntity<User> getById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null)
            return ResponseEntity.ok(user);
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<User> register(User user) {
        user.setSalt(BCrypt.gensalt());
        user.setPassword(BCrypt.hashpw(user.getPassword(), user.getSalt()));
        userRepository.save(user);
        return ResponseEntity.created(URI.create("/users/" + user.getId())).body((user));
    }

    @Override
    public ResponseEntity<User> login(User userToCheck) {
        String username = userToCheck.getUsername();
        String passwordToCheck = userToCheck.getPassword();
        User user = userRepository.findByUsername(username);

        if(user == null || !BCrypt.checkpw(passwordToCheck, user.getPassword()))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<User> edit(Long id, User userDetails) {
        User user = userRepository.findById(id).orElse(null);
        if(user != null) {
            user.setUsername(userDetails.getUsername());
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
