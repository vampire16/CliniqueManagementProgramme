package com.bridgelabz.service;

import com.bridgelabz.exception.CliniqueManagementException;
import com.bridgelabz.model.Appointment;
import com.bridgelabz.model.Doctor;
import com.bridgelabz.model.Patient;
import com.bridgelabz.model.Report;
import com.bridgelabz.utility.FileSystem;

import java.io.IOException;
import java.util.*;

public class CliniqueManagementService implements CliniqueManagement {

    //    OBJECTS
    FileSystem fileSystem = new FileSystem();
    HashMap<Appointment, Integer> appoint = new HashMap<>();

    //    VARIABLE
    Integer appointment;

    @Override
    public <E> void add(E object, String path, Class<E> classObject) throws IOException {
        ArrayList<E> list = fileSystem.readData(path, classObject);
        list.add(object);
        fileSystem.writeData(path, list);
    }

    @Override
    public Doctor searchDoctorById(int id, String path) throws IOException {
        return fileSystem.readData(path, Doctor.class).stream().filter(doctor -> doctor.getId() == id).findFirst().get();
    }

    public boolean isDoctorAvailableById(int id, String path) throws IOException {
        return fileSystem.readData(path, Doctor.class).stream().filter(doctor -> doctor.getId() == id).findFirst().isPresent();
    }

    @Override
    public Patient searchPatientById(int id, String path) throws IOException {
        return fileSystem.readData(path, Patient.class).stream().filter(patient -> patient.getId() == id).findFirst().get();
    }

    @Override
    public Doctor searchDoctorByName(String name, String path) throws IOException {
        return fileSystem.readData(path, Doctor.class).stream().filter(doctor -> doctor.getName().equals(name)).findFirst().get();
    }

    @Override
    public Patient searchPatientByName(String name, String path) throws IOException {
        return fileSystem.readData(path, Patient.class).stream().filter(patient -> patient.getName().equals(name)).findFirst().get();
    }

    @Override
    public Doctor searchDoctorBySpecialization(String specialization, String path) throws IOException {
        return fileSystem.readData(path, Doctor.class).stream().filter(doctor -> doctor.getSpecialization().equals(specialization)).findFirst().get();
    }

    @Override
    public void makeAppointment(int did, String path, Date date, int pid) throws CliniqueManagementException, IOException {
        boolean isDoctorAvailable = isDoctorAvailableById(did, path);
        if (isDoctorAvailable == true) {
            ArrayList<Doctor> doctors = fileSystem.readData("src/test/resources/DoctorsFile.json", Doctor.class);
            Doctor doctor = doctors.stream().filter(d -> d.getId() == did).findFirst().get();
            if (date != null) {
                String s = date.toString();
                Appointment newAppoint = new Appointment(did, date);
                if (appoint.containsKey(newAppoint)) {
                    appointment = appoint.get(newAppoint);
                    appointment++;
                    if (appointment <= 5) {
                        appoint.put(newAppoint, appointment);
                        Report report = new Report(did, pid, doctor.getSpecialization(), s);
                        this.add(report, "src/test/resources/Appointments.json", Report.class);
                    } else {
                        throw new CliniqueManagementException(CliniqueManagementException.Exceptions.APPOINTMENTS_FULL);
                    }
                } else {
                    appoint.put(newAppoint, 1);
                    Report report = new Report(did, pid, doctor.getSpecialization(), s);
                    this.add(report, "src/test/resources/Appointments.json", Report.class);
                }
            } else {
                throw new CliniqueManagementException(CliniqueManagementException.Exceptions.DATE_FORMAT_INCORRECT);
            }
        } else {
            throw new CliniqueManagementException(CliniqueManagementException.Exceptions.DOCTOR_UNAVAILABLE);
        }
    }

    @Override
    public void patientReport(int pid) throws IOException {
        ArrayList<Report> reportList = fileSystem.readData("src/test/resources/Appointments.json", Report.class);
        Report report = reportList.stream().filter(e -> e.getPid() == pid).findFirst().get();
        ArrayList<Doctor> doctorsList = fileSystem.readData("src/test/resources/DoctorsFile.json", Doctor.class);
        Doctor doctor = doctorsList.stream().filter(d -> d.getId() == report.getDid()).findFirst().get();
        ArrayList<Patient> patientsList = fileSystem.readData("src/test/resources/PatientFile.json", Patient.class);
        Patient patient = patientsList.stream().filter(p -> p.getId() == report.getPid()).findFirst().get();
        report(doctor, patient, report);
    }

    //    TO PRINT REPORT
    public void report(Doctor doctor, Patient patient, Report report) {
        System.out.println("Date : " + report.getDate() +
                "\nDoctor name : " + doctor.getName() +
                "\nDoctor specialization : " + doctor.getSpecialization() +
                "\nPatient name : " + patient.getName() +
                "\nPatient age : " + patient.getAge() +
                "\nSpecial care : Home quarantine for 14 days \n Drugs : AT-100 and OYA1");
    }

    @Override
    public long printAndCountAppointmentsWithDoctors() {
        appoint.entrySet().stream().forEach(entry -> System.out.println("Doctor id " + entry.getKey().getDid() +
                " has " + entry.getValue() + " appointments on " + entry.getKey().getDate()));
        return appoint.entrySet().stream().count();
    }

    @Override
    public Doctor getPopularDoctor() throws IOException {
        ArrayList<Report> reportList = fileSystem.readData("src/test/resources/Appointments.json", Report.class);
        ArrayList list = new ArrayList();
        reportList.stream().forEach(entry -> list.add(entry.getDid()));
        Object o = this.mostCommon(list);
        ArrayList<Doctor> doctors = fileSystem.readData("src/test/resources/DoctorsFile.json", Doctor.class);
        Doctor doctor = doctors.stream().filter(d -> d.getId() == (Integer) o).findFirst().get();
        return doctor;
    }

    @Override
    public Doctor getPopularSpecialization() throws IOException {
        ArrayList<Report> reportList = fileSystem.readData("src/test/resources/Appointments.json", Report.class);
        ArrayList list = new ArrayList();
        reportList.stream().forEach(entry -> list.add(entry.getSpecialization()));
        Object o = this.mostCommon(list);
        ArrayList<Doctor> doctors = fileSystem.readData("src/test/resources/DoctorsFile.json", Doctor.class);
        Doctor doctor = doctors.stream().filter(d -> d.getSpecialization().equals(o)).findFirst().get();
        return doctor;
    }

    //    TO GET MOST COMMON OBJECT FROM LIST
    public <T> T mostCommon(List<T> list) {
        Map<T, Integer> map = new HashMap<>();
        list.stream().forEach(e -> map.put(e, map.get(e) == null ? 1 : map.get(e) + 1));
        Map.Entry<T, Integer> max = null;
        for (Map.Entry<T, Integer> e : map.entrySet()) {
            if (max == null || e.getValue() > max.getValue())
                max = e;
        }
        return max.getKey();
    }

}
