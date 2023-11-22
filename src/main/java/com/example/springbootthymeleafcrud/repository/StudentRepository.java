package com.example.springbootthymeleafcrud.repository;

import com.example.springbootthymeleafcrud.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
