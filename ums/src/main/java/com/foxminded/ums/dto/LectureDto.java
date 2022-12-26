package com.foxminded.ums.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.foxminded.ums.entities.Subject;
import com.foxminded.ums.entities.Teacher;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LectureDto {
    @Schema(description = "Unique UUID identifier of the Lecture.",
            example = "12611b1e-b277-4e64-8ff3-243a5d6fbc2d", required = true)
    private UUID id;

    @Schema(description = "Topic of the Lecture.",
           example = "SQL", required = true)
    @NotNull @Size(max=255)
    private String topic;

    @Schema(description = "Description of the Lecture.",
            example = "SQL - Structured Query Language", required = true)
    @NotNull @Size(max=1024)
    private String description;

    @Schema(description = "Teacher Object.",
            required = true)
    @NotNull
    private Teacher teacher;

    @Schema(description = "Subject Object.",
            required = true)
    @NotNull
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
        return Objects.hashCode(id);
    }
}
