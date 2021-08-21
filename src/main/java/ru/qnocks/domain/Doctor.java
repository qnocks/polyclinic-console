package ru.qnocks.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "full_name")
    @NotBlank(message = "This field cannot be blank")
    @Size(max = 25, message = "Full name's length must be up to 25 characters")
    private String fullName;

    @Column(name = "specialty")
    @NotBlank(message = "This field cannot be blank")
    private String specialty;

    @Column(name = "office_number")
    @NotNull(message = "This field can't be null")
    @Min(value = 1, message = "The office number cannot be less then 1")
    private Integer officeNumber;

    @Column(name = "schedule")
    @NotBlank(message = "This field cannot be blank")
    @Pattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]-([01]?[0-9]|2[0-3]):[0-5][0-9]",
            message = "Doctor's schedule should match pattern HH:MM-HH:MM 24 hours")
    private String schedule;

    public Doctor() {
    }

    public Doctor(String fullName, String specialty, Integer officeNumber, String schedule) {
        this.fullName = fullName;
        this.specialty = specialty;
        this.officeNumber = officeNumber;
        this.schedule = schedule;
    }

    public Doctor(Long id, String fullName, String specialty, Integer officeNumber, String schedule) {
        this.id = id;
        this.fullName = fullName;
        this.specialty = specialty;
        this.officeNumber = officeNumber;
        this.schedule = schedule;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Integer getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(Integer officeNumber) {
        this.officeNumber = officeNumber;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {

        // 14:22-16:33
        String[] times = schedule.split("-"); // [14:22, 16:33]
        String[] startHoursAndMins = times[0].split(":"); // [14, 22]
        String[] endHoursAndMins = times[1].split(":"); // [16, 33]

        int startHours = Integer.parseInt(startHoursAndMins[0]);
        int startMins = Integer.parseInt(startHoursAndMins[1]);
        int endHours = Integer.parseInt(endHoursAndMins[0]);
        int endMins = Integer.parseInt(endHoursAndMins[1]);

        // 14 > 16
        if (startHours > endHours || ((startHours == endHours) && startMins > endMins)) {
            throw new IllegalArgumentException("Schedule is incorrect");
        }

        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", specialty='" + specialty + '\'' +
                ", officeNumber=" + officeNumber +
                ", schedule='" + schedule + '\'' +
                '}';
    }
}
