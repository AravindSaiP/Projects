package com.springMVC.studentManagementSystem.service;

import com.springMVC.studentManagementSystem.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();

    void saveStudent(Student student);

    Student getStudentById(Long id);

    void deleteStudent(Long id);
}
