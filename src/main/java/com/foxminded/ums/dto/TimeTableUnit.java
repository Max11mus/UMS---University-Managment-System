package com.foxminded.ums.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

//@Entity
//@Table(name = "time_table_unit")
public class TimeTableUnit {
    @Id
    @GeneratedValue
    @Column(name = "time_table_unit_id")
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne(optional = false)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @ManyToMany
    @JoinTable(name = "time_table_unit_groups",
            joinColumns = @JoinColumn(name = "time_table_unit_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Group> groups = new HashSet<>();

    @Column(name = "begin", nullable = false)
    private LocalDateTime begin;

    @Column(name = "end", nullable = false)
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

        TimeTableUnit that = (TimeTableUnit) o;

        if (!id.equals(that.id)) return false;
        if (!location.equals(that.location)) return false;
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
