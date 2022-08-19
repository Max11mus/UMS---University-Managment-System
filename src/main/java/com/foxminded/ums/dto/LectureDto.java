package com.foxminded.ums.dto;

import com.foxminded.ums.entities.Subject;
import com.foxminded.ums.entities.Teacher;

import java.util.UUID;

public class LectureDto {
    private UUID id;
    private String topic;
    private String description;
    private Teacher teacher;
    private Subject subject;

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

        LectureDto that = (LectureDto) o;

        if (!id.equals(that.id)) return false;
        if (!topic.equals(that.topic)) return false;
        if (!description.equals(that.description)) return false;
        if (!teacher.equals(that.teacher)) return false;
        return subject.equals(that.subject);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
