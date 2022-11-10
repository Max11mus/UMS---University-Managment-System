package com.foxminded.ums.entities;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "lecture", schema = "ums")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Lecture {
    @Id
    @GeneratedValue
    @Column(name = "lecture_id")
    private UUID id;

    @NotNull
    @Column(name = "topic")
    private String topic;

    @Column(name = "description")
    private String description;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "subject_id")
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
