package com.foxminded.ums.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.foxminded.ums.validation.OlderThanSixteen;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;
import java.util.TimeZone;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeacherDto {
    @Schema(description = "Unique UUID identifier of the Teacher.",
            example = "12611b1e-b277-4e64-8ff3-243a5d6fbc2d", required = true)
    private UUID id;

    @Schema(description = "Name of the Teacher.",
            example = "Suzanne", required = true)
    @NotNull @NotBlank @Size(max=20)
    private String name;

    @Schema(description = "Surname of the Teacher.",
            example = "Gallagher", required = true)
    @NotNull @NotBlank @Size(max=20)
    private String surname;

    @Schema(description = "Birth Date of Teacher in YYYY-mm-DD format",
            example = "1973-10-20",
            required = true)
    @NotNull
    @OlderThanSixteen
    private LocalDate birthDate;

    @Schema(description = "TimeZone of Teacher",
            example = "UTC",
            required = false,
            nullable = true)
    private TimeZone timeZone;

    @Schema(description = "Login of Teacher, must be uniqe",
            example = "Suzi.Gallagher",
            required = true)
    @NotNull @NotBlank @Size(max=255)
    private String login;

    @Schema(description = "Email of Teacher, must be uniqe",
            example = "Suzi.Gallagher@gmail.com",
            required = true)
    @NotNull @NotBlank @Size(max=255)
    private String email;

    @Schema(description = "AvatarLink of Teacher",
            example = "https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50.jpg",
            required = false,
            nullable = true)
    @Size(max=1024)
    private String avatarPath;

    @Schema(description = "Hashed Password of Teacher ",
            example = "",
            required = true)
    @NotNull @Size(max=255)
    private String hashedPassword;

    @Schema(description = "Academic Degree of Teacher",
            example = "Master of Science",
            required = true)
    @NotNull @NotBlank @Size(max=255)
    private String academicDegree;

    @Schema(description = "Employment Date of the Teacher in YYYY-mm-DD format",
            example = "2022-07-07",
            required = true)
    @NotNull
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
        return Objects.hashCode(id);
    }

}
