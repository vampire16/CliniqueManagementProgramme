package com.bridgelabz.service;

import com.bridgelabz.exception.CliniqueManagementException;
import com.bridgelabz.model.Doctor;
import com.bridgelabz.model.Patient;

import java.io.IOException;
import java.util.Date;

public interface CliniqueManagement {
    public abstract <E> void add(E object, String path, Class<E> classObject) throws IOException;

    public abstract Doctor searchDoctorById(int id, String path) throws IOException;

    public abstract Patient searchPatientById(int id, String path) throws IOException;

    public abstract Doctor searchDoctorByName(String name, String path) throws IOException;

    public abstract Patient searchPatientByName(String name, String path) throws IOException;

    public abstract Doctor searchDoctorBySpecialization(String specialization, String path) throws IOException;

    public abstract void makeAppointment(int did, String path, Date date) throws CliniqueManagementException, IOException;

    long printAndCountAppointments();

    int getPopularDoctor();

    Doctor getPopularSpecialization() throws IOException;
}
