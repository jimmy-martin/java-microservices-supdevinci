package com.ms.student.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ms.student.dtos.SchoolDto;
import com.ms.student.dtos.StudentDto;
import com.ms.student.models.Student;
import com.ms.student.repositories.StudentRepository;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private RestTemplate msSchoolRestTemplate;

    @Autowired
    public StudentService(StudentRepository studentRepository, RestTemplate msSchoolRestTemplate) {
        this.studentRepository = studentRepository;
        this.msSchoolRestTemplate = msSchoolRestTemplate;
    }

    public StudentDto find(String id) {
        Student student = studentRepository.findById(id).orElse(null);

        if (student == null) {
            throw new RuntimeException("Student not found");
        }

        SchoolDto schoolDto = msSchoolRestTemplate
                .getForObject("/schools/{id}", SchoolDto.class, student.getSchoolId());

        return new StudentDto(
                student.getId(),
                student.getName(),
                student.getSex(),
                schoolDto);
    }

    public Student create(Student student) {
        return studentRepository.save(student);
    }
}
