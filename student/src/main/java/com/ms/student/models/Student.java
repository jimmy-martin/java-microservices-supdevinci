package com.ms.student.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Student {
    @Id
    private String id;
    private String name;
    private String sex;
    private Long schoolId;

    public Student(String name, String sex, Long schoolId) {
        this.name = name;
        this.sex = sex;
        this.schoolId = schoolId;
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

    public Long getSchoolId() {
        return schoolId;
    }
}
