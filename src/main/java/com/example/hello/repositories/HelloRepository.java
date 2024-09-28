package com.example.hello.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.hello.domain.Hello;

public interface HelloRepository extends JpaRepository<Hello, Integer>{
    List<Hello> findByTitle(String title);

    @Query("SELECT t FROM Hello t WHERE t.title ILIKE ?1")
    List<Hello> findByTitleLike (String title);
}
