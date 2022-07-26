package com.foxminded.ums.entities;

import java.util.Set;
import java.util.UUID;

public class Group {
    private UUID id;
    private String name;
    private Set<Student> students;

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

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (!id.equals(group.id)) return false;
        if (!name.equals(group.name)) return false;
        return students.equals(group.students);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
