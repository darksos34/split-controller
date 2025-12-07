package com.jda.coder.splitcontroller.controller;

import com.jda.coder.splitcontroller.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api")
public interface UserApi {

    @GetMapping("/users/{uuid}")
    ResponseEntity<User> getUser(@PathVariable("uuid") UUID uuid);

    @PostMapping("/users")
    ResponseEntity<User> createUser(@RequestBody User user);

    @GetMapping
    ResponseEntity<List<User>> getAll();

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id);
}


