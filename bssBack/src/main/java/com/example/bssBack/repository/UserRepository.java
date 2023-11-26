package com.example.bssBack.repository;

import com.example.bssBack.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findBySid(String sid);

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsBySid(String sid);

}
