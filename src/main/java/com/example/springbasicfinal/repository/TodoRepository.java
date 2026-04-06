package com.example.springbasicfinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbasicfinal.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByOrderByUpdatedAtDesc();
}
