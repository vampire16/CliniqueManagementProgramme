package com.bridgelabz.service;

import com.bridgelabz.exception.CliniqueManagementException;
import com.bridgelabz.model.Doctor;
import com.bridgelabz.model.Patient;

import java.io.IOException;
import java.util.Date;

public interface CliniqueManagement {

    //    TO ADD OBJECTS IN JSON FILE
    <E> void add(E object, String path, Class<E> classObject) throws IOException;

    //    TO SEARCH DOCTOR BY ID
    Doctor searchDoctorById(int id, String path) throws IOException;

    //    TO SEARCH PATIENT BY ID
    Patient searchPatientById(int id, String path) throws IOException;

    //    TO SEARCH DOCTOR BY NAME
    Doctor searchDoctorByName(String name, String path) throws IOException;

    //    TO SEARCH PATIENT BY NAME
    Patient searchPatientByName(String name, String path) throws IOException;

    //    TO SEARCH DOCTOR BY SPECIALIZATION
    Doctor searchDoctorBySpecialization(String specialization, String path) throws IOException;

    //    TO MAKE APPOINTMENT WITH DOCTOR
    void makeAppointment(int did, String path, Date date, int pid) throws CliniqueManagementException, IOException;

    //    TO GET PATIENT REPORT
    void patientReport(int pid) throws IOException;

    //    TO PRINT AND TO GET COUNT OF APPOINTMENTS
    long printAndCountAppointmentsWithDoctors();

    //    TO GET POPULAR DOCTOR
    Doctor getPopularDoctor() throws IOException;

    //    TO GET POPULAR SPECIALIZATION
    Doctor getPopularSpecialization() throws IOException;
}
