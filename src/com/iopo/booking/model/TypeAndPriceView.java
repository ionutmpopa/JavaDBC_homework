package com.iopo.booking.model;

import java.util.Objects;

public class TypeAndPriceView {

    private String type;
    private double value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Type: " + type + "; Value: " + value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeAndPriceView that = (TypeAndPriceView) o;
        return Double.compare(that.value, value) == 0 &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(type, value);
    }
}
