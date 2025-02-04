package com.ms.student.dtos;

public class SchoolDto {
    private Long id;
    private String name;
    private String address;
    private String directorName;

    public SchoolDto(Long id, String name, String address, String directorName) {
        this.id = id;
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
