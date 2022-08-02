package com.foxminded.ums.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;


@Entity
@Table(name = "teacher", schema = "ums")
public class Teacher {
    @Id
    @GeneratedValue
    @Column(name = "teacher_id")
    private UUID id;

    @OneToOne
    @JoinColumn(name = "person_id")
    private PersonInfo personInfo;

    @Column(name = "academic_degree")
    private String academicDegree;

    @Column(name = "employment_date")
    private LocalDate employmentDate;

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
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
