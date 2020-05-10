package com.bridgelabz.service;

import com.bridgelabz.exception.CliniqueManagementException;
import com.bridgelabz.model.Doctor;
import com.bridgelabz.model.Patient;
import com.bridgelabz.utility.Appointment;
import com.bridgelabz.utility.CliniqueManagement;
import com.bridgelabz.utility.FileSystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CliniqueManagementApplication extends CliniqueManagement {
    FileSystem fileSystem = new FileSystem();
    HashMap<Appointment, Integer> appoint = new HashMap<>();

//    @Override
//    public <E> void add(E object, String path) throws IOException {
//        ArrayList<E> list = fileSystem.readData(path);
//        list.add(object);
//        fileSystem.writeData(path, list);
//    }

    @Override
    public void addDoctor(Doctor doctor, String path) throws IOException {
        ArrayList<Doctor> list = fileSystem.readDoctorData(path);
        list.add(doctor);
        fileSystem.writeDoctorData(path, list);
    }

    @Override
    public void addPatient(Patient patient, String path) throws IOException {
        ArrayList<Patient> list = fileSystem.readPatientData(path);
        list.add(patient);
        fileSystem.writePatientData(path, list);
    }

    @Override
    public Doctor searchDoctorById(int id, String path) throws IOException {
        ArrayList<Doctor> list = fileSystem.readDoctorData(path);
        for (Doctor doctor : list) {
            if (doctor.getId() == id) {
                return doctor;
            }
        }
        return null;
    }

    @Override
    public Patient searchPatientById(int id, String path) throws IOException {
        ArrayList<Patient> list = fileSystem.readPatientData(path);
        for (Patient Patient : list) {
            if (Patient.getId() == id) {
                return Patient;
            }
        }
        return null;
    }

    @Override
    public Doctor searchDoctorByName(String name, String path) throws IOException {
        ArrayList<Doctor> list = fileSystem.readDoctorData(path);
        for (Doctor doctor : list) {
            if (doctor.getName().equals(name)) {
                return doctor;
            }
        }
        return null;
    }

    @Override
    public Patient searchPatientByName(String name, String path) throws IOException {
        ArrayList<Patient> list = fileSystem.readPatientData(path);
        for (Patient Patient : list) {
            if (Patient.getName().equals(name)) {
                return Patient;
            }
        }
        return null;
    }

    @Override
    public Doctor searchDoctorBySpecialization(String specialization, String path) throws IOException {
        ArrayList<Doctor> list = fileSystem.readDoctorData(path);
        for (Doctor doctor : list) {
            if (doctor.getSpecialization().equals(specialization)) {
                return doctor;
            }
        }
        return null;
    }

    public void makeAppointment(int did, String path, Date date) throws IOException, CliniqueManagementException {
        Doctor doctor = searchDoctorById(did, path);
        if (doctor != null) {
            if (date != null) {
                Appointment newApp = new Appointment(did, date);
                if (appoint.containsKey(newApp)) {
                    Integer appointment = appoint.get(newApp);
                    appointment++;
                    if (appointment <= 5) {
                        appoint.put(newApp, appointment);
                    } else {
                        throw new CliniqueManagementException(CliniqueManagementException.Exceptions.APPOINTMENTS_FULL);
                    }
                } else {
                    appoint.put(newApp, 1);
                }
            } else {
                throw new CliniqueManagementException(CliniqueManagementException.Exceptions.DATE_FORMAT_INCORRECT);
            }
        } else {
            throw new CliniqueManagementException(CliniqueManagementException.Exceptions.DOCTOR_UNAVAILABLE);
        }
    }

    public void printAppointments() {
        for (Map.Entry<Appointment, Integer> entry : appoint.entrySet()) {
            System.out.println("Doctor id "+entry.getKey().getDid() + " has " + entry.getValue() + " appointments on "+ entry.getKey().getDate());
        }
    }
}
