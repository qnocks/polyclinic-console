package ru.qnocks.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Entity
@Table(name = "observations")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Observation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "observation_time")
    @Past
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NonNull
    private LocalDate observationDate;

    @Column(name = "diagnosis")
    @NonNull
    private String diagnosis;

    @ManyToOne
    @NonNull
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    @NonNull
    private Doctor doctor;
}
