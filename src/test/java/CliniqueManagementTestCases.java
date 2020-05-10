import com.bridgelabz.exception.CliniqueManagementException;
import com.bridgelabz.model.Appointment;
import com.bridgelabz.model.Doctor;
import com.bridgelabz.model.Patient;
import com.bridgelabz.service.CliniqueManagementService;
import com.bridgelabz.utility.FileSystem;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class CliniqueManagementTestCases {
    ObjectMapper mapper = new ObjectMapper();
    CliniqueManagementService application;
    FileSystem fileSystem;
    String doctorFilePath = "src/test/resources/DoctorsFile.json";
    String patientFilePath = "src/test/resources/PatientFile.json";

    @Before
    public void setUp() throws CliniqueManagementException {
        application = new CliniqueManagementService();
        fileSystem = new FileSystem();
    }

    @Test
    public void givenDoctorInfo_WhenAddedInFile_ShouldReturnFile() throws IOException {
        application.addDoctor(new Doctor("vaibhav", 100, "corona", "8AM"), doctorFilePath);
        application.addDoctor(new Doctor("bharat", 440, "cancer", "11AM"), doctorFilePath);
        application.addDoctor(new Doctor("babu", 123, "hanta", "2PM"), doctorFilePath);
        ArrayList<Doctor> doctorsList = mapper.readValue(new File(doctorFilePath), new TypeReference<ArrayList<Doctor>>() {
        });
        Assert.assertEquals("bharat", doctorsList.get(1).getName());
    }

    @Test
    public void givenPatientInfo_WhenAddedInFile_ShouldReturnFile() throws IOException {
        application.addPatient(new Patient("sachin", 1, "3294294933", 23), patientFilePath);
        application.addPatient(new Patient("nitin", 2, "2376468234", 19), patientFilePath);
        application.addPatient(new Patient("bhiku", 3, "23634823", 29), patientFilePath);
        ArrayList<Patient> patientsList = mapper.readValue(new File(patientFilePath), new TypeReference<ArrayList<Patient>>() {
        });
        Assert.assertEquals(29, patientsList.get(2).getAge());
    }

    @Test
    public void givenDoctorId_WhenSearchDoctorById_ShouldReturnDoctorInfo() throws IOException {
        Doctor doctor = application.searchDoctorById(440, doctorFilePath);
        Assert.assertEquals("bharat", doctor.getName());
    }

    @Test
    public void givenPatientId_WhenSearchPatientById_ShouldReturnPatientInfo() throws IOException {
        Patient patient = application.searchPatientById(3, patientFilePath);
        Assert.assertEquals(29, patient.getAge());
    }

    @Test
    public void givenDoctorName_WhenSearchDoctorByName_ShouldReturnDoctorInfo() throws IOException {
        Doctor doctor = application.searchDoctorByName("vaibhav", doctorFilePath);
        Assert.assertEquals(100, doctor.getId());
    }

    @Test
    public void givenPatientName_WhenSearchPatientByName_ShouldReturnPatientInfo() throws IOException {
        Patient patient = application.searchPatientByName("sachin", patientFilePath);
        Assert.assertEquals(1, patient.getId());
    }

    @Test
    public void givenDoctorSpecialization_WhenSearchDoctorBySpecialization_ShouldReturnDoctorInfo() throws IOException {
        Doctor doctor = application.searchDoctorBySpecialization("hanta", doctorFilePath);
        Assert.assertEquals("babu", doctor.getName());
    }

    @Test
    public void givenIdOfDoctorAndDate_WhenAppointmentFixed_ShouldReturnDoctorInfo() throws ParseException, IOException, CliniqueManagementException {
        int id1 = 100,id2 = 100,id3 = 440;
        String dateInString = "10-Jan-2020";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        Date date = formatter.parse(dateInString);
        application.makeAppointment(id1, doctorFilePath,date);
        application.makeAppointment(id2, doctorFilePath,date);
        String dateInString1 = "12-Jan-2020";
        SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
        Date date1 = formatter1.parse(dateInString1);
        application.makeAppointment(id3, doctorFilePath,date1);
        long l = application.printAndCountAppointments();
        Assert.assertEquals(2,l);
    }

    @Test
    public void givenIdOfDoctorAndDate_WhenAppointmentIsNotAvailable_ShouldThrowException() throws ParseException, IOException {
        int id = 100;
        String dateInString = "10-Jan-2020";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        Date date = formatter.parse(dateInString);
        try {
            application.makeAppointment(id, doctorFilePath,date);
            application.makeAppointment(id, doctorFilePath,date);
            application.makeAppointment(id, doctorFilePath,date);
            application.makeAppointment(id, doctorFilePath,date);
            application.makeAppointment(id, doctorFilePath,date);
            application.makeAppointment(id, doctorFilePath,date);
        } catch (CliniqueManagementException e) {
            Assert.assertEquals(e.type, CliniqueManagementException.Exceptions.APPOINTMENTS_FULL);
        }
    }

    @Test
    public void givenIdOfDoctorAndDate_WhenDateIsNotCorrect_ShouldThrowException() throws IOException {
        int id = 100;
        Date date = null;
        try {
            application.makeAppointment(id, doctorFilePath, date);
        } catch (CliniqueManagementException e) {
            Assert.assertEquals(e.type, CliniqueManagementException.Exceptions.DATE_FORMAT_INCORRECT);
        }
    }

    @Test
    public void givenIdOfDoctorAndDate_WhenIdIsNotCorrect_ShouldThrowException() throws ParseException, IOException {
        int id = 101;
        String dateInString = "10-Jan-2020";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        Date date = formatter.parse(dateInString);
        try {
            application.makeAppointment(id, doctorFilePath, date);
        } catch (CliniqueManagementException e) {
            Assert.assertEquals(e.type, CliniqueManagementException.Exceptions.DOCTOR_UNAVAILABLE);
        }
    }
}
