package com.foxminded.ums.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;
import java.util.TimeZone;
import java.util.UUID;

@MappedSuperclass
public abstract class Person {

    @Id
    @GeneratedValue
    @Column(name = "person_id")
    private UUID id;

    @NotNull
    @Column(name = "person_name")
    private String name;

    @NotNull
    @Column(name = "surname")
    private String surname;

    @NotNull
    @Column(name = "birthDate")
    private LocalDate birthDate;

    @Column(name = "timeZone")
    private TimeZone timeZone;

    @NotNull
    @Column(name = "login", unique = true)
    private String login;

    @NotNull
    @Email
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "avatar_path")
    private String avatarPath;

    @NotNull
    @Column(name = "hashed_password")
    private String hashedPassword;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id)
                && Objects.equals(name, person.name)
                && Objects.equals(surname, person.surname)
                && Objects.equals(birthDate, person.birthDate)
                && Objects.equals(timeZone, person.timeZone)
                && Objects.equals(login, person.login)
                && Objects.equals(email, person.email)
                && Objects.equals(avatarPath, person.avatarPath)
                && Objects.equals(hashedPassword, person.hashedPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
