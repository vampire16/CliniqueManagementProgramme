package com.bridgelabz.model;

public class Doctor {
    //    PRIVATE VARIABLES
    private String name;
    private int id;
    private String specialization;
    private String availability;

    //    DEFAULT CONSTRUCTOR
    public Doctor() {
    }

    //    PARAMETERIZE CONSTRUCTOR
    public Doctor(String name, int id, String specialization, String availability) {
        this.name = name;
        this.id = id;
        this.specialization = specialization;
        this.availability = availability;
    }

    //    GETTER AND SETTER METHODS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
