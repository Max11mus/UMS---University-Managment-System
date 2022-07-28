package com.foxminded.ums.dto;

import java.time.LocalDate;
import java.util.TimeZone;
import java.util.UUID;

public class PersonInfo {
    private UUID id;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private TimeZone timeZone;
    private String login;
    private String email;
    private String avatarPath;
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

        PersonInfo that = (PersonInfo) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (!surname.equals(that.surname)) return false;
        if (!birthDate.equals(that.birthDate)) return false;
        if (!timeZone.equals(that.timeZone)) return false;
        if (!login.equals(that.login)) return false;
        if (!email.equals(that.email)) return false;
        if (!avatarPath.equals(that.avatarPath)) return false;
        return hashedPassword.equals(that.hashedPassword);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
