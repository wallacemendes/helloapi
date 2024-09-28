package com.example.hello.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.hello.domain.User;



public interface UserRepository extends JpaRepository<User, String> {
    UserDetails findByLogin(String login);

}
