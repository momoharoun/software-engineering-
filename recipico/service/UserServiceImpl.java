package com.example.recipico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.recipico.model.User;
import com.example.recipico.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isPresent())
            return userOpt.get();
        else
            throw new RuntimeException("user not found.");
    }

    // Get user by email and password
    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password);
        if(user != null)
            return user;
        else
            throw new RuntimeException("user not found.");
    }

    @Override
    public void saveUser(User user) {
        User userDetail = userRepository.save(user);
        System.out.println("user saved to db with userId : " + userDetail.getId());
    }

    @Override
    public void updateUser(User user, Long userId) {
        Optional<User> userDetailOpt = userRepository.findById(userId);
        if(userDetailOpt.isPresent()){
            User userDetail = userDetailOpt.get();
            if(user.getFirstName() != null || user.getFirstName().isEmpty())
                userDetail.setFirstName(user.getFirstName());
            if(user.getPassword() != null || user.getPassword().isEmpty())
                userDetail.setPassword(user.getPassword());
            if(user.getEmail() != null || user.getEmail().isEmpty())
                userDetail.setEmail(user.getEmail());
            userRepository.save(userDetail);
        }else{
            throw new RuntimeException("user not found.");
        }
    }

    @Override
    public void deleteUseryId(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isPresent())
            userRepository.deleteById(userId);
        else
            throw new RuntimeException("user not found.");
    }
}
