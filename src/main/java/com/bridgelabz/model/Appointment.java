package com.bridgelabz.model;

import java.util.Date;
import java.util.Objects;

public class Appointment {
    private int did;
    private Date date;

    public Appointment(int did, Date date) {
        this.did = did;
        this.date = date;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return did == that.did &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(did, date);
    }

}
