package com.foxminded.ums.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

//@Entity
//@Table(name = "location", schema = "public")
public class Location {
    @Id
    @GeneratedValue
    @Column(name = "location_id")
    private UUID id;

    @Column(name = "address")
    private String address;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (!id.equals(location.id)) return false;
        return address.equals(location.address);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
