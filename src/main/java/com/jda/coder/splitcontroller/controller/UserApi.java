package com.jda.coder.splitcontroller.controller;

import com.jda.coder.splitcontroller.model.User;
import com.jda.coder.splitcontroller.requestmapping.RequestPath;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping(RequestPath.API)
public interface UserApi {

    @GetMapping(RequestPath.USER_UUID)
    ResponseEntity<User> getUser(@PathVariable("uuid") UUID uuid);

    @PostMapping(RequestPath.USERS)
    ResponseEntity<User> createUser(@RequestBody User user);

    @GetMapping
    ResponseEntity<List<User>> getAll();

    @DeleteMapping(RequestPath.USER_ID_PATH)
    ResponseEntity<Void> delete(@PathVariable UUID id);
}


