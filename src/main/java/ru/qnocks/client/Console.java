package ru.qnocks.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.qnocks.domain.Patient;
import ru.qnocks.enums.District;

import java.time.LocalDate;
import java.util.Scanner;

@Component
public class Console {

    private static Scanner sc;
    private static ApplicationFacade app;

    @Autowired
    public Console(ApplicationFacade app, Scanner sc) {
        Console.app = app;
        Console.sc = sc;
    }

    public static void doctors() {
        try {
            System.out.println("Doctors: ");
            app.getAllDoctors().forEach(System.out::println);
            System.out.println();
        } catch (Exception e) {
            System.out.println("No doctors found");
        }
    }

    public static void createDoctor() {
        try {
            System.out.println("Enter full name:");
            String fullName = sc.nextLine();
            System.out.println("Enter specialty:");
            String specialty = sc.nextLine();
            System.out.println("Enter office number:");
            int officeNumber = Integer.parseInt(sc.nextLine());
            System.out.println("Enter schedule following the pattern (HH:MM-HH:MM):");
            String schedule = sc.nextLine();
            app.createDoctor(fullName, specialty, officeNumber, schedule);
        } catch (Exception e) {
            System.out.println("The error occurred! " + e);
        }
    }

    public static void updateDoctor() {
        try {
            doctors();
            System.out.println("Enter doctor's id to update: ");
            long id = Long.parseLong(sc.nextLine());
            System.out.println("Enter new full name:");
            String fullName = sc.nextLine();
            System.out.println("Enter new specialty:");
            String specialty = sc.nextLine();
            System.out.println("Enter new office number:");
            int officeNumber = Integer.parseInt(sc.nextLine());
            System.out.println("Enter new schedule following the pattern (HH:MM-HH:MM):");
            String schedule = sc.nextLine();
            app.updateDoctor(id, fullName, specialty, officeNumber, schedule);
        } catch (Exception e) {
            System.out.println("The error occurred! " + e);
        }
    }

    public static void deleteDoctor() {
        try {
            doctors();
            System.out.println("Enter doctor's id to delete: ");
            long id = sc.nextLong();
            app.deleteDoctor(id);
        } catch (Exception e) {
            System.out.println("The error occurred! " + e);
        }
    }

    public static void patients() {
        try {
            System.out.println("Patients: ");
            app.getAllPatients().forEach(System.out::println);
            System.out.println();
        } catch (Exception e) {
            System.out.println("The error occurred! " + e);
        }
    }

    public static void patientByFullName() {
        try {
            System.out.println("Enter patient's name to find: ");
            // TODO: Request to input part for the naive search (1)
            String fullName = sc.nextLine();
            Patient foundPatient = app.getPatientByFullName(fullName);
            System.out.println(foundPatient);
            System.out.println();
        } catch (Exception e) {
            System.out.println("The error occurred! " + e);
        }
    }

    public static void patientAllByDistrict() {
        try {
            System.out.println("Enter patient's district to find: ");
            District district = District.valueOf(sc.nextLine());
            app.getAllPatientsByDistrict(district).forEach(System.out::println);
            System.out.println();
        } catch (Exception e) {
            System.out.println("The error occurred! " + e);
        }
    }

    public static void createPatient() {
        try {
            System.out.println("Enter full name:");
            String fullName = sc.nextLine();
            System.out.println("Enter birthdate:");
            String birthdate = sc.nextLine();
            System.out.println("Enter district:");
            District district = District.valueOf(sc.nextLine());
            app.createPatient(fullName, birthdate, district);
        } catch (Exception e) {
            System.out.println("The error occurred! " + e);
        }
    }

    public static void updatePatient() {
        try {
            patients();
            System.out.println("Enter patient's id to update: ");
            long id = Long.parseLong(sc.nextLine());

            System.out.println("Enter new full name:");
            String fullName = sc.nextLine();
            System.out.println("Enter new birthdate:");
            String birthdate = sc.nextLine();
            System.out.println("Enter new district:");
            District district = District.valueOf(sc.nextLine());

            app.updatePatient(id, fullName, birthdate, district);
        } catch (Exception e) {
            System.out.println("The error occurred! " + e);
        }
    }

    public static void deletePatient() {
        try {
            patients();
            System.out.println("Enter patient's id to delete: ");
            long id = sc.nextLong();
            app.deletePatient(id);
        } catch (Exception e) {
            System.out.println("The error occurred! " + e);
        }
    }

    public static void observation() {
        try {
            patients();
            System.out.println("Enter patient's id: ");
            Long patientId = Long.valueOf(sc.nextLine());

            System.out.println("Observation: ");
            app.getAllObservationByPatient(patientId).forEach(System.out::println);
            System.out.println();
        } catch (Exception e) {
            System.out.println("The error occurred! " + e);
        }
    }

    public static void createObservation() {
        try {
            doctors();
            patients();
            System.out.println("Enter doctor's id: ");
            Long doctorId = Long.valueOf(sc.nextLine());
            System.out.println("Enter patient's id: ");
            Long patientId = Long.valueOf(sc.nextLine());
            System.out.println("Enter observation's time: ");
            LocalDate observationDate = LocalDate.parse(sc.nextLine());
            System.out.println("Enter diagnosis: ");
            String diagnosis = sc.nextLine();
            app.createObservation(observationDate, diagnosis, patientId, doctorId);
        } catch (Exception e) {
            System.out.println("The error occurred! " + e);
        }
    }

    public static void updateObservation() {
        try {
            System.out.println("Enter observation's id to update: ");
            long id = Long.parseLong(sc.nextLine());
            doctors();
            patients();
            System.out.println("Enter new doctor's id: ");
            Long doctorId = Long.valueOf(sc.nextLine());
            System.out.println("Enter new patient's id: ");
            Long patientId = Long.valueOf(sc.nextLine());

            System.out.println("Enter new observation's time:");
            LocalDate observationDate = LocalDate.parse(sc.nextLine());
            System.out.println("Enter new diagnosis:");
            String diagnosis = sc.nextLine();

            app.updateObservation(id, observationDate, diagnosis, patientId, doctorId);

        } catch (Exception e) {
            System.out.println("The error occurred! " + e);
        }
    }

    public static void deleteObservation() {
        try {
            observation();
            System.out.println("Enter observation's id to delete: ");
            long id = sc.nextLong();
            app.deleteObservation(id);
        } catch (Exception e) {
            System.out.println("The error occurred! " + e);
        }
    }

    public static void countObservationsByPatientDistrict() {
        System.out.println("Enter district to count observations in it");
        District district= District.valueOf(sc.nextLine());
        int count = app.countObservationsByPatientDistrict(district);
        System.out.println("Observation's count in " + district + " is " + count);
    }

    public static void countObservationsByMonth() {
        System.out.println("Enter month to count observations for it");
        System.out.println("01/02/03/... corresponding to January/February/March/...");
        String month = sc.nextLine();
        int count = app.countObservationsByMonth(month);
        System.out.println("Observation's count for " + month + " month is " + count);
    }

    public static void clear() {
        for (int i = 0; i < 30; i++) System.out.println();
    }

    public static void menu() {
        System.out.println();
        System.out.println("=============MENU================");
        System.out.println("1. List of doctors");
        System.out.println("2. List of patients");
        System.out.println("3. Add a doctor");
        System.out.println("4. Add a patient");
        System.out.println("5. Update a doctor");
        System.out.println("6. Update a patient");
        System.out.println("7. Delete a doctor");
        System.out.println("8. Delete a patient");
        System.out.println("9. Find a patient by full name");
        System.out.println("10. Find patients by district");
        System.out.println("11. List of observations");
        System.out.println("12. Add an observation");
        System.out.println("13. Update an observation");
        System.out.println("14. Delete an observation");
        System.out.println("15. Count observations in certain district");
        System.out.println("16. Count observations for given month");
        System.out.println("q. Exit");
        System.out.println("=================================");
        System.out.println();
    }
}
