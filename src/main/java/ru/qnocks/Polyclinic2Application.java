package ru.qnocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.qnocks.client.Console;

import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
public class Polyclinic2Application implements CommandLineRunner {

    // TODO: 2. Input validation
    // TODO: 3. Add Patient's finding opportunity

    private final Scanner sc;
    private String ans = "0";
    @Autowired
    public Polyclinic2Application(Scanner sc) {
        this.sc = sc;
    }

    @Override
    public void run(String... args) throws Exception {

        Console.menu();

        while (!ans.equals("q")) {
            ans = sc.nextLine();
            chooseAction(ans);
            Console.menu();
        }
    }

    private void chooseAction(String ans) {
        switch (ans) {
            case "1":
                Console.doctors();
                break;
            case "2":
                Console.patients();
                break;
            case "3":
                Console.createDoctor();
                break;
            case "4":
                Console.createPatient();
                break;
            case "5":
                Console.updateDoctor();
                break;
            case "6":
                Console.updatePatient();
                break;
            case "7":
                Console.deleteDoctor();
                break;
            case "8":
                Console.deletePatient();
                break;
            case "9":
                Console.patientByFullName();
                break;
            case "10":
                Console.patientAllByDistrict();
                break;
            case "11":
                Console.observation();
                break;
            case "12":
                Console.createObservation();
                break;
            case "13":
                Console.updateObservation();
                break;
            case "14":
                Console.deleteObservation();
                break;
            case "15":
                Console.countObservationsByPatientDistrict();
                break;
            case "16":
                Console.countObservationsByMonth();
                break;
        }
    }

    public static void main(String[] args) throws IOException {
        SpringApplication app = new SpringApplication(Polyclinic2Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

}
