package com.foxminded.ums.entities;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name = "teacher", schema = "ums")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Teacher extends Person {

    @Column(name = "academic_degree")
    private String academicDegree;

    @Column(name = "employment_date")
    private LocalDate employmentDate;

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
        if (!(o instanceof Teacher)) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(academicDegree, teacher.academicDegree)
                && Objects.equals(employmentDate, teacher.employmentDate);
    }

}
