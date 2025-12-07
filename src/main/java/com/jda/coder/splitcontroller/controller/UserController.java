package com.jda.coder.splitcontroller.controller;

import com.jda.coder.splitcontroller.model.User;
import com.jda.coder.splitcontroller.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;


    @Override
    public ResponseEntity<User> getUser(UUID uuid) {
        return ResponseEntity.ok(userService.findById(uuid));    }

    @Override
    public ResponseEntity<User> createUser(User user) {
        User saved = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Override
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @Override
    public ResponseEntity<Void> delete(UUID id) {
        return ResponseEntity.noContent().build();
    }
}
