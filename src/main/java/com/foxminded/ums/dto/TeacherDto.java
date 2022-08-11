package com.foxminded.ums.dto;

import com.foxminded.ums.entities.Person;
import com.foxminded.ums.entities.Teacher;

import java.time.LocalDate;
import java.util.Objects;
import java.util.TimeZone;
import java.util.UUID;

public class TeacherDto {
    private UUID id;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private TimeZone timeZone;
    private String login;
    private String email;
    private String avatarPath;
    private String hashedPassword;
    private String academicDegree;
    private LocalDate employmentDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
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
        TeacherDto that = (TeacherDto) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(surname, that.surname)
                && Objects.equals(birthDate, that.birthDate)
                && Objects.equals(timeZone, that.timeZone)
                && Objects.equals(login, that.login)
                && Objects.equals(email, that.email)
                && Objects.equals(avatarPath, that.avatarPath)
                && Objects.equals(hashedPassword, that.hashedPassword)
                && Objects.equals(academicDegree, that.academicDegree)
                && Objects.equals(employmentDate, that.employmentDate);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
