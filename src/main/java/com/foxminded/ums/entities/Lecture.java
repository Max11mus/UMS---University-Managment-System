package com.foxminded.ums.entities;

import java.util.UUID;

public class Lecture {
    private UUID id;
    private String topic;
    private String description;
    private Teacher teacher;
    private Subject subject;

    public Lecture(UUID id,
                   String topic,
                   String description,
                   Teacher teacher,
                   Subject subject) {
        this.id = id;
        this.topic = topic;
        this.description = description;
        this.teacher = teacher;
        this.subject = subject;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lecture lecture = (Lecture) o;

        if (!id.equals(lecture.id)) return false;
        if (!topic.equals(lecture.topic)) return false;
        if (!description.equals(lecture.description)) return false;
        if (!teacher.equals(lecture.teacher)) return false;
        return subject.equals(lecture.subject);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
