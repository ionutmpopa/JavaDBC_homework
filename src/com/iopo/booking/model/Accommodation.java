package com.iopo.booking.model;

import java.util.Objects;

public class Accommodation {

    private int id;
    private String type;
    private String bedType;
    private int maxGuests;
    private String Description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public int getMaxGuests() {
        return maxGuests;
    }

    public void setMaxGuests(int maxGuests) {
        this.maxGuests = maxGuests;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public String toString() {
        return "Accommodation{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", bedType='" + bedType + '\'' +
                ", maxGuests=" + maxGuests +
                ", Description='" + Description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accommodation that = (Accommodation) o;
        return id == that.id &&
                maxGuests == that.maxGuests &&
                Objects.equals(type, that.type) &&
                Objects.equals(bedType, that.bedType) &&
                Objects.equals(Description, that.Description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type, bedType, maxGuests, Description);
    }
}
