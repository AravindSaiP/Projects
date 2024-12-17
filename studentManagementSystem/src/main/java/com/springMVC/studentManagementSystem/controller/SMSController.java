package com.springMVC.studentManagementSystem.controller;

import com.springMVC.studentManagementSystem.entity.Student;
import com.springMVC.studentManagementSystem.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class SMSController {

    private StudentService studentService;

    public SMSController(StudentService studentService) {
        this.studentService = studentService;
    }

    // handler method to handle list students and return model and view
    @GetMapping("/student")
    public String getStudents(Model model){
        model.addAttribute("student", studentService.getAllStudents());
        return "students";
    }
    @GetMapping("/student/new")
    public String addNewStudent(Model model){
        Student student = new Student();
        log.info("CREATING SAVE PAGE");
        model.addAttribute("student",student);
        return "create_student";

    }
    @PostMapping("/student")
    public String saveStudent(@ModelAttribute("student") Student student){
        studentService.saveStudent(student);
        return "redirect:/student";
    }

    @GetMapping("/student/edit/{id}")
    public String editStudent(@PathVariable Long id, Model model){
        log.info("IN GET MAPPING FOR EDIT STUDENT");
        log.info(studentService.getStudentById(id).toString());
        model.addAttribute("student",studentService.getStudentById(id));
        return "edit_student";
    }

    @PostMapping("/student/update")
    public String updateStudent(@ModelAttribute("student") Student student){
        Student existingStudent = studentService.getStudentById(student.getId());
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        studentService.saveStudent(existingStudent);
        return "redirect:/student";
    }

    @GetMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return "redirect:/student";
    }
}
