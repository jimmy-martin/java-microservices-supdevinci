package com.ms.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.school.models.School;
import com.ms.school.repositories.SchoolRepository;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;

    @Autowired
    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public Iterable<School> all() {
        return schoolRepository.findAll();
    }

    public School find(Long id) {
        return schoolRepository.findById(id).orElse(null);
    }

    public School create(School school) {
        return schoolRepository.save(school);
    }

    public void delete(Long id) {
        schoolRepository.deleteById(id);
    }
}
