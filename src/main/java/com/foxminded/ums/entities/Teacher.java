package com.foxminded.ums.entities;

import java.util.UUID;

public class Teacher {
    private UUID id;
    private PersonInfo personInfo;
    private String academicDegree;
    private String employmentDate;

    public Teacher(UUID id,
                   PersonInfo personInfo,
                   String academicDegree,
                   String employmentDate) {
        this.id = id;
        this.personInfo = personInfo;
        this.academicDegree = academicDegree;
        this.employmentDate = employmentDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }

    public String getAcademicDegree() {
        return academicDegree;
    }

    public void setAcademicDegree(String academicDegree) {
        this.academicDegree = academicDegree;
    }

    public String getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(String employmentDate) {
        this.employmentDate = employmentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;

        if (!id.equals(teacher.id)) return false;
        if (!personInfo.equals(teacher.personInfo)) return false;
        if (!academicDegree.equals(teacher.academicDegree)) return false;
        return employmentDate.equals(teacher.employmentDate);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
