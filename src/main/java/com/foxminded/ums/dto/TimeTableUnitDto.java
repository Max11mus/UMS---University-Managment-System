package com.foxminded.ums.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.foxminded.ums.entities.Group;
import com.foxminded.ums.entities.Lecture;
import com.foxminded.ums.entities.Location;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TimeTableUnitDto {
    private UUID id;
    private Location location;
    private Lecture lecture;
    private Set<Group> groups = new HashSet<>();
    private LocalDateTime begin;
    private LocalDateTime end;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public LocalDateTime getBegin() {
        return begin;
    }

    public void setBegin(LocalDateTime begin) {
        this.begin = begin;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeTableUnitDto that = (TimeTableUnitDto) o;

        if (!id.equals(that.id)) return false;
        if (!location.equals(that.location)) return false;
        if (!lecture.equals(that.lecture)) return false;
        if (!groups.equals(that.groups)) return false;
        if (!begin.equals(that.begin)) return false;
        return end.equals(that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
