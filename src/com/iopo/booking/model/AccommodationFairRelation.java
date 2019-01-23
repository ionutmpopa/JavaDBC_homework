package com.iopo.booking.model;

import java.util.Objects;

public class AccommodationFairRelation {

    private int id;
    private int idAccommodation;
    private int idRoomFair;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAccommodation() {
        return idAccommodation;
    }

    public void setIdAccommodation(int idAccommodation) {
        this.idAccommodation = idAccommodation;
    }

    public int getIdRoomFair() {
        return idRoomFair;
    }

    public void setIdRoomFair(int idRoomFair) {
        this.idRoomFair = idRoomFair;
    }

    @Override
    public String toString() {
        return "AccommodationFairRelation{" +
                "id=" + id +
                ", idAccommodation=" + idAccommodation +
                ", idRoomFair=" + idRoomFair +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccommodationFairRelation that = (AccommodationFairRelation) o;
        return id == that.id &&
                idAccommodation == that.idAccommodation &&
                idRoomFair == that.idRoomFair;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, idAccommodation, idRoomFair);
    }
}
