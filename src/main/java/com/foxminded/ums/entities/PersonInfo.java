package com.foxminded.ums.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.TimeZone;
import java.util.UUID;

@Entity
@Table(name = "person_info", schema = "ums")
public class PersonInfo {

    @Id
    @GeneratedValue
    @Column(name = "personinfo_id")
    private UUID id;

    @Column(name = "name_")
    private String name;

    @Column(name = "surname")
    private String surname;
    @Column(name = "birthDate")
    private LocalDate birthDate;

    @Column(name = "timeZone")
    private TimeZone timeZone;

    @Column(name = "login")
    private String login;

    @Column(name = "email")
    private String email;

    @Column(name = "avatar_path")
    private String avatarPath;

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
