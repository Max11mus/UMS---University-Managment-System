package com.foxminded.ums.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "subject", schema = "ums")
public class Subject {
    @Id
    @GeneratedValue
    @Column(name = "subject_id")
    UUID id;

    @Column(name = "subject_name")
    String name;

    @Column(name = "description")
    String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        if (!id.equals(subject.id)) return false;
        if (!name.equals(subject.name)) return false;
        return description.equals(subject.description);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
