package com.springMVC.studentManagementSystem.repository;

import com.springMVC.studentManagementSystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Student findStudentById(Long id);
}
