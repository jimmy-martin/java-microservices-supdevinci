package com.ms.student.controllers;

import com.ms.student.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ms.student.dtos.StudentDto;
import com.ms.student.services.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public Iterable<Student> getStudents() {
        return studentService.all();
    }

    @GetMapping("/{id}")
    public StudentDto getStudent(@PathVariable String id) {
        return studentService.find(id);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.create(student);
    }
}
