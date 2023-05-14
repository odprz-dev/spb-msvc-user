package com.msvc.usuario.controllers;


import com.msvc.usuario.entities.User;
import com.msvc.usuario.exceptions.BadRequestException;
import com.msvc.usuario.exceptions.ResourceNotFoundException;
import com.msvc.usuario.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<?> getUsers() {
        List<User> result = userService.getUsers();
        return ResponseEntity.ok(result);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {

//        try {
            User result = userService.getUserById(id);
            return ResponseEntity.ok(result);

//        } catch(ResourceNotFoundException resourceNotFoundException){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resourceNotFoundException);
//        }
    }


    @PostMapping("")
    public ResponseEntity<?> postUser(@RequestBody User body) {
        try {
            User result = userService.postUser(body);
            return ResponseEntity.created(URI.create("api/v1/user/" + result.getUserId())).body(result);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putUser(@RequestBody User body, @PathVariable String id) {
        User result = userService.updateUser(id, body);
        return ResponseEntity.ok(result);

    }

}
