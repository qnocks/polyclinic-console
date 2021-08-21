package ru.qnocks.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.qnocks.controllers.DoctorsController;
import ru.qnocks.controllers.ObservationsController;
import ru.qnocks.controllers.PatientsControllers;
import ru.qnocks.domain.Doctor;
import ru.qnocks.domain.Observation;
import ru.qnocks.domain.Patient;
import ru.qnocks.enums.District;

import java.time.LocalDate;
import java.util.List;

@Component
public class ApplicationFacade {

    private final DoctorsController doctorsController;
    private final PatientsControllers patientsControllers;
    private final ObservationsController observationsController;

    @Autowired
    public ApplicationFacade(DoctorsController doctorsController,
                             PatientsControllers patientsControllers,
                             ObservationsController observationsController) {
        this.doctorsController = doctorsController;
        this.patientsControllers = patientsControllers;
        this.observationsController = observationsController;
    }

    public void createDoctor(String fullName, String specially, int officeNumber, String schedule) {
        doctorsController.save(new Doctor(fullName, specially, officeNumber, schedule));
    }

    public void updateDoctor(Long id, String fullName, String specially, int officeNumber, String schedule) {
        doctorsController.update(id, new Doctor(fullName, specially, officeNumber, schedule));
    }

    public void deleteDoctor(Long id) {
        doctorsController.delete(id);
    }

    public Doctor getDoctor(Long id) {
        return doctorsController.get(id);
    }

    public List<Doctor> getAllDoctors() {
        return doctorsController.getAll();
    }

    public void createPatient(String fullName, String birthDate, District district) {
        patientsControllers.save(new Patient(fullName, birthDate, district));
    }

    public void updatePatient(Long id, String fullName, String birthDate, District district) {
        patientsControllers.update(id, new Patient(fullName, birthDate, district));
    }

    public void deletePatient(Long id) {
        patientsControllers.delete(id);
    }

    public Patient getPatient(Long id) {
        return patientsControllers.get(id);
    }

    public List<Patient> getAllPatients() {
        return patientsControllers.getAll();
    }

    public Patient getPatientByFullName(String fullName) {
        return patientsControllers.getByFullName(fullName);
    }

    public List<Patient> getAllPatientsByDistrict(District district) {
        return patientsControllers.getAllByDistrict(district);
    }

    public void createObservation(LocalDate observationDate, String diagnosis, Long patientId, Long doctorId) {
        Patient patient = getPatient(patientId);
        Doctor doctor = getDoctor(doctorId);
        observationsController.save(new Observation(observationDate, diagnosis, patient, doctor));
    }

    public void updateObservation(Long id, LocalDate observationTime, String diagnosis, Long patientId, Long doctorId) {
        Patient patient = getPatient(patientId);
        Doctor doctor = getDoctor(doctorId);
        observationsController.update(id, new Observation(observationTime, diagnosis, patient, doctor));
    }

    public void deleteObservation(Long id) {
        observationsController.delete(id);
    }

    public List<Observation> getAllObservation() {
        return observationsController.getAll();
    }

    public List<Observation> getAllObservationByPatient(Long patientId) {
        Patient patient = getPatient(patientId);
        return observationsController.getAllByPatient(patient);
    }

    public int countObservationsByPatientDistrict(District district) {
        return observationsController.countByPatientDistrict(district);
    }

    public int countObservationsByMonth(String month) {
        return observationsController.countByMonth(month);
    }
}