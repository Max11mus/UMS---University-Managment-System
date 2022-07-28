package com.foxminded.ums.dto;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class TimeTableUnit {
    private UUID id;
    private Location place;
    private Lecture lecture;
    private Set<Group> groups;
    private LocalDateTime begin;
    private LocalDateTime end;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Location getPlace() {
        return place;
    }

    public void setPlace(Location place) {
        this.place = place;
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

        TimeTableUnit that = (TimeTableUnit) o;

        if (!id.equals(that.id)) return false;
        if (!place.equals(that.place)) return false;
        if (!lecture.equals(that.lecture)) return false;
        if (!groups.equals(that.groups)) return false;
        if (!begin.equals(that.begin)) return false;
        return end.equals(that.end);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
