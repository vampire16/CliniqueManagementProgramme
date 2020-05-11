package com.bridgelabz.model;

public class Report {

    //    PRIVATE VARIABLES
    private int did;
    private int pid;
    private String specialization;
    private String date;

    //    DEFAULT CONSTRUCTOR
    public Report() {
    }

    //    PARAMETERIZE CONSTRUCTOR
    public Report(int did, int pid, String specialization, String date) {
        this.did = did;
        this.pid = pid;
        this.specialization = specialization;
        this.date = date;
    }

    //    GETTER AND SETTER METHODS
    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
