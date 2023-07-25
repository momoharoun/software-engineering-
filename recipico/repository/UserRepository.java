package com.example.recipico.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.recipico.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM users u WHERE u.email = :email AND u.passsword = :password")
    User findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
    
}
