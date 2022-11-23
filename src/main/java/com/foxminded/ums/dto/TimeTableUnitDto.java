package com.foxminded.ums.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.foxminded.ums.entities.Group;
import com.foxminded.ums.entities.Lecture;
import com.foxminded.ums.entities.Location;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TimeTableUnitDto {
    @Schema(description = "Unique UUID identifier of the TimeTableUnit.",
            example = "12611b1e-b277-4e64-8ff3-243a5d6fbc2d", required = true)
    private UUID id;

    @Schema(description = "Location Object.",
            required = false)
    private Location location;

    @Schema(description = "Lecture Object.",
            required = false)
    private Lecture lecture;

    @Schema(description = "Array of Group Object.",
            required = false)
    private Set<Group> groups = new HashSet<>();

    @Schema(description = "Time of beginning in format: yyyy-mm-dd HH:mm:ss.SSS",
            example = "2022-06-04 09:20:00.000",
            required = false)
    private LocalDateTime begin;

    @Schema(description = "Time of ending in format: yyyy-mm-dd HH:mm:ss.SSS",
            example = "2022-06-04 09:20:00.000",
            required = false)
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
