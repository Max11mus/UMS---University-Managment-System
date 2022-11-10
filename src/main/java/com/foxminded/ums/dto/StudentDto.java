package com.foxminded.ums.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.foxminded.ums.validation.OlderThanSixteen;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;
import java.util.TimeZone;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDto {
    private UUID id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String surname;

    @NotNull
    @OlderThanSixteen
    private LocalDate birthDate;

    private TimeZone timeZone;

    @NotNull
    @NotBlank
    private String login;

    @NotNull
    @Email
    private String email;

    private String avatarPath;

    @NotNull
    private String hashedPassword;

    private LocalDate dropoutDate;

    @NotNull
    private LocalDate enrollDate;

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

    public LocalDate getDropoutDate() {
        return dropoutDate;
    }

    public void setDropoutDate(LocalDate dropoutDate) {
        this.dropoutDate = dropoutDate;
    }

    public LocalDate getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(LocalDate enrollDate) {
        this.enrollDate = enrollDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDto that = (StudentDto) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(surname, that.surname)
                && Objects.equals(birthDate, that.birthDate)
                && Objects.equals(timeZone, that.timeZone)
                && Objects.equals(login, that.login)
                && Objects.equals(email, that.email)
                && Objects.equals(avatarPath, that.avatarPath)
                && Objects.equals(hashedPassword, that.hashedPassword)
                && Objects.equals(dropoutDate, that.dropoutDate)
                && Objects.equals(enrollDate, that.enrollDate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


}
