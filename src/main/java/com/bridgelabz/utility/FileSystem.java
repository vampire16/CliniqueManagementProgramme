package com.bridgelabz.utility;

import com.bridgelabz.model.Doctor;
import com.bridgelabz.model.Patient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FileSystem {
    ObjectMapper mapper = new ObjectMapper();

    public FileSystem() {
    }

//    public <E> void writeData(String filePath, ArrayList<E> list) throws IOException {
//        mapper.writeValue(new File(filePath), list);
//    }
//
//    public <E> ArrayList<E> readData(String filePath) throws IOException {
//        return mapper.readValue(new File(filePath), new TypeReference<ArrayList<E>>() {
//        });
//    }

    public void writeDoctorData(String filePath, ArrayList<Doctor> list) throws IOException {
        mapper.writeValue(new File(filePath), list);
    }

    public ArrayList<Doctor> readDoctorData(String filePath) throws IOException {
        return mapper.readValue(new File(filePath), new TypeReference<ArrayList<Doctor>>() {
        });
    }

    public void writePatientData(String filePath, ArrayList<Patient> list) throws IOException {
        mapper.writeValue(new File(filePath), list);
    }

    public ArrayList<Patient> readPatientData(String filePath) throws IOException {
        return mapper.readValue(new File(filePath), new TypeReference<ArrayList<Patient>>() {
        });
    }
}
