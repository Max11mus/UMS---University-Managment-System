package com.foxminded.ums.entities;

import java.util.UUID;

public class Subject {
    UUID id;
    String name;
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
