package com.ms.student.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ms.student.models.Student;

public interface StudentRepository extends MongoRepository<Student, String> {

}
