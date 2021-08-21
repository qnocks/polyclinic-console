package ru.qnocks.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Entity
@Table(name = "observations")
public class Observation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "observation_time")
    @Past
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate observationDate;

    @Column(name = "diagnosis")
    private String diagnosis;

    @ManyToOne
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;

    public Observation() {
    }

    public Observation(LocalDate observationDate, String diagnosis, Patient patient, Doctor doctor) {
        this.observationDate = observationDate;
        this.diagnosis = diagnosis;
        this.patient = patient;
        this.doctor = doctor;
    }

    public LocalDate getObservationDate() {
        return observationDate;
    }

    public void setObservationTime(LocalDate observationDate) {
        this.observationDate = observationDate;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "Observation{" +
                "id=" + id +
                ", observationDate=" + observationDate +
                ", diagnosis='" + diagnosis + '\'' +
                ", doctor=" + doctor +
                '}';
    }
}
