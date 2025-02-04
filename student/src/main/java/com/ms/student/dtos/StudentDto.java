package com.ms.student.dtos;

public class StudentDto {
    private String id;
    private String name;
    private String sex;
    private SchoolDto school;

    public StudentDto(String id, String name, String sex, SchoolDto school) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.school = school;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public SchoolDto getSchool() {
        return school;
    }
}
