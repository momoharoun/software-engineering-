package com.example.recipico.service;

import java.util.List;

import com.example.recipico.model.User;


public interface UserService {
    List<User> getAllUser();

    User getUserById(Long userId);

    User getUserByEmailAndPassword(String email, String password);

    void saveUser(User user);

    void updateUser(User user, Long userid);

    void deleteUseryId(Long userId);
}
