package com.example.recipico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.recipico.model.User;
import com.example.recipico.service.UserService;

@RestController
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUser(){
        List<User> users = userService.getAllUser();
        System.out.println("users : "+users);
        return users;
    }
    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable Long userId){
        User user = userService.getUserById(userId);
        System.out.println("userId : "+userId+" : user : "+user);
        return user;
    }
    
    @GetMapping("/login/{email}")
    public User getUserById(@PathVariable String email, @RequestHeader("password") String password) {
    // User user = userService.getUserById(userId);
    User user = userService.getUserByEmailAndPassword(email, password);
    System.out.println("userId: " + user.getId()+ " : user: " + user.getEmail());
    return user;
    }

    @PostMapping("/users")
    public String saveUser(@RequestBody User user){
        userService.saveUser(user);
        return "user saved successfuly.";
    }

    @PutMapping("/users/{userId}")
    public String updateUser(@RequestBody User user, @PathVariable Long userId){
        userService.updateUser(user, userId);
        return "user updated successfully.";
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUseryId(@PathVariable Long userId){
        userService.deleteUseryId(userId);
        return "user deleted successfully.";
    }
}
