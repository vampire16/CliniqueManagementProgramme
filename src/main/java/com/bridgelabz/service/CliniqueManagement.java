package com.bridgelabz.utility;

import com.bridgelabz.model.Doctor;
import com.bridgelabz.model.Patient;

import java.io.IOException;

public abstract class CliniqueManagement {
    public abstract void addDoctor(Doctor doctor, String path) throws IOException;

    public abstract void addPatient(Patient patient, String path) throws IOException;

    public abstract Doctor searchDoctorById(int id, String path) throws IOException;

    public abstract Patient searchPatientById(int id, String path) throws IOException;

    public abstract Doctor searchDoctorByName(String name, String path) throws IOException;

    public abstract Patient searchPatientByName(String name, String path) throws IOException;

    public abstract Doctor searchDoctorBySpecialization(String specialization, String path) throws IOException;
//    public abstract <E> void add(E object, String path) throws IOException;

//    public abstract <E> void update(E object, String path) throws IOException;

//    public abstract void update(Doctor object, String path) throws IOException;

//    public abstract <E> Doctor searchById(int id, String path) throws IOException;
}
