package com.bridgelabz.model;

public class Patient {
    //    PRIVATE VARIABLES
    private String name;
    private int id;
    private String phone;
    private int age;

    //    DEFAULT CONSTRUCTOR
    public Patient() {
    }

    //    PARAMETERIZE CONSTRUCTOR
    public Patient(String name, int id, String phone, int age) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.age = age;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
