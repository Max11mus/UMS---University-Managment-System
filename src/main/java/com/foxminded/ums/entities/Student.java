package com.foxminded.ums.entities;

import java.time.LocalDate;
import java.util.UUID;

public class Student {
    private UUID id;
    private PersonInfo personInfo;
    private LocalDate enrollDate;
    private LocalDate dropoutDate;

    public void setId(UUID id) {
        this.id = id;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }

    public LocalDate getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(LocalDate enrollDate) {
        this.enrollDate = enrollDate;
    }

    public LocalDate getDropoutDate() {
        return dropoutDate;
    }

    public void setDropoutDate(LocalDate dropoutDate) {
        this.dropoutDate = dropoutDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (!id.equals(student.id)) return false;
        if (!personInfo.equals(student.personInfo)) return false;
        if (!enrollDate.equals(student.enrollDate)) return false;
        return dropoutDate.equals(student.dropoutDate);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
