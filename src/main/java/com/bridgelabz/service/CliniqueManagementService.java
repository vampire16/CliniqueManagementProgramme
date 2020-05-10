package com.bridgelabz.service;

import com.bridgelabz.exception.CliniqueManagementException;
import com.bridgelabz.model.Appointment;
import com.bridgelabz.model.Doctor;
import com.bridgelabz.model.Patient;
import com.bridgelabz.utility.FileSystem;

import java.io.IOException;
import java.util.*;

public class CliniqueManagementService extends CliniqueManagement {
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
        return fileSystem.readDoctorData(path).stream().filter(doctor -> doctor.getId() == id).findFirst().get();
    }

    public boolean isDoctorAvailableById(int id, String path) throws IOException {
        return fileSystem.readDoctorData(path).stream().filter(doctor -> doctor.getId() == id).findFirst().isPresent();
    }

    @Override
    public Patient searchPatientById(int id, String path) throws IOException {
        return fileSystem.readPatientData(path).stream().filter(patient -> patient.getId() == id).findFirst().get();
    }

    @Override
    public Doctor searchDoctorByName(String name, String path) throws IOException {
        return fileSystem.readDoctorData(path).stream().filter(doctor -> doctor.getName().equals(name)).findFirst().get();
    }

    @Override
    public Patient searchPatientByName(String name, String path) throws IOException {
        return fileSystem.readPatientData(path).stream().filter(patient -> patient.getName().equals(name)).findFirst().get();
    }

    @Override
    public Doctor searchDoctorBySpecialization(String specialization, String path) throws IOException {
        return fileSystem.readDoctorData(path).stream().filter(doctor -> doctor.getSpecialization().equals(specialization)).findFirst().get();
    }

    @Override
    public void makeAppointment(int did, String path, Date date) throws CliniqueManagementException, IOException {
        boolean doctor = isDoctorAvailableById(did, path);
        if (doctor == true) {
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

    public long printAndCountAppointments() {
        appoint.entrySet().stream().forEach(entry -> System.out.println("Doctor id "+entry.getKey().getDid() +
                " has " + entry.getValue() + " appointments on "+ entry.getKey().getDate()));
        return appoint.entrySet().stream().count();
    }
}
