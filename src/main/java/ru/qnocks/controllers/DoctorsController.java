package ru.qnocks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.qnocks.domain.Doctor;
import ru.qnocks.services.DoctorsService;

import java.util.List;

@Component
public class DoctorsController {
    private final DoctorsService doctorsService;

    @Autowired
    public DoctorsController(DoctorsService doctorsService) {
        this.doctorsService = doctorsService;
    }

    public List<Doctor> getAll() {
        return doctorsService.getALl();
    }

    public Doctor get(Long id) {
        return doctorsService.getById(id);
    }

    public void save(Doctor doctor) {
        doctorsService.save(doctor);
    }

    public void update(Long id, Doctor doctor) {
        doctorsService.update(id, doctor);
    }

    public void delete(Long id) {
        doctorsService.delete(id);
    }
}
