package com.example.oath2authenticator.TiuOath2Activity.repositories;

import com.example.oath2authenticator.TiuOath2Activity.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}