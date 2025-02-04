package com.ms.school.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String directorName;

    public School() {
    }

    public School(String name, String address, String directorName) {
        this.name = name;
        this.address = address;
        this.directorName = directorName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDirectorName() {
        return directorName;
    }
}
