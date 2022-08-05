package com.foxminded.ums.dto;

import com.foxminded.ums.entities.Person;
import com.foxminded.ums.entities.Teacher;

import java.time.LocalDate;
import java.util.UUID;

public class TeacherDto {
    private UUID id;
    private Person personInfo;
    private String academicDegree;
    private LocalDate employmentDate;

    public void setPersonInfo(Person personInfo) {
        this.personInfo = personInfo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Person getPersonInfo() {
        return personInfo;
    }

    public String getAcademicDegree() {
        return academicDegree;
    }

    public void setAcademicDegree(String academicDegree) {
        this.academicDegree = academicDegree;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeacherDto teacher = (TeacherDto) o;

        if (!id.equals(teacher.id)) return false;
        if (!personInfo.equals(teacher.personInfo)) return false;
        if (!academicDegree.equals(teacher.academicDegree)) return false;
        return employmentDate.equals(teacher.employmentDate);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public Teacher convertToEntity(){
        Teacher entity = new Teacher();

        entity.setId(this.getId());
//        entity.setPersonInfo(this.getPersonInfo());
        entity.setAcademicDegree(this.getAcademicDegree());
        entity.setEmploymentDate(this.getEmploymentDate());

        return  entity;
    }
}
