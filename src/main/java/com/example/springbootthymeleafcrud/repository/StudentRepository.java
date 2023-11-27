package com.example.springbootthymeleafcrud.repository;

import com.example.springbootthymeleafcrud.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query("Select s from Student  s where s.name like %:keyword% or s.email like %:keyword%")
    List<Student> searchStudent(@Param("keyword")String keyword);
}
