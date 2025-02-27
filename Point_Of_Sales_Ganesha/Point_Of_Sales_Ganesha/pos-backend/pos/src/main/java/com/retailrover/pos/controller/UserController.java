package com.retailrover.pos.controller;

import com.retailrover.pos.entity.User;
import com.retailrover.pos.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")

public class UserController{

    private final UserService userService;

    @PostMapping("/user")
    public User postuser(@RequestBody User user) {
        return userService.post(user);
    }

    @GetMapping("/user")
    public List<User> getAllAppointments(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        try{
            userService.deleteUser(id);
            return new ResponseEntity<>( "User with ID" + id + " deleted successfully", HttpStatus.OK);

        }catch(EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserByID(@PathVariable Long id){
        User user = userService.getUserByID(id);
        if(user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user){
        User updatedUser = userService.updateUser(id,user);

        if(updatedUser == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.ok(updatedUser);
    }





}
