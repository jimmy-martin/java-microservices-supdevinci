package com.ms.school.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.*;

import com.ms.school.models.School;
import com.ms.school.services.SchoolService;

@RestController
@RequestMapping("/schools")
public class SchoolController {
    private final SchoolService schoolService;
    private final ServerProperties serverProperties;
    @Autowired
    public SchoolController(SchoolService schoolService, ServerProperties serverProperties) {
        this.schoolService = schoolService;
        this.serverProperties = serverProperties;
    }

    @GetMapping
    public Iterable<School> getSchools() {
        System.out.println("Server port: " + serverProperties.getPort());
        return schoolService.all();
    }

    @GetMapping("/{id}")
    public School getSchool(@PathVariable Long id) {
        System.out.println("Server port: " + serverProperties.getPort());
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
