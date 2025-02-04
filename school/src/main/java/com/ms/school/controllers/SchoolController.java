package com.ms.school.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ms.school.models.School;
import com.ms.school.services.SchoolService;

@RestController
@RequestMapping("/schools")
public class SchoolController {
    private final SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping
    public Iterable<School> getSchools() {
        return schoolService.all();
    }

    @GetMapping("/{id}")
    public School getSchool(@PathVariable Long id) {
        return schoolService.find(id);
    }

    @PostMapping
    public School createSchool(@RequestBody School school) {
        return schoolService.create(school);
    }

    @DeleteMapping("/{id}")
    public void deleteSchool(@PathVariable Long id) {
        schoolService.delete(id);
    }
}
