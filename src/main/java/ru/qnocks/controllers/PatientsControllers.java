package ru.qnocks.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.qnocks.domain.Patient;
import ru.qnocks.domain.enums.District;
import ru.qnocks.services.PatientsService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PatientsControllers {
    private final PatientsService patientsService;

    public List<Patient> getAll() {
        return patientsService.getALl();
    }

    public Patient get(Long id) {
        return patientsService.getById(id);
    }

    public void save(Patient patient) {
        patientsService.save(patient);
    }

    public void update(Long id, Patient patient) {
        patientsService.update(id, patient);
    }

    public void delete(Long id) {
        patientsService.delete(id);
    }

    public Patient getByFullName(String fullName) {
        return patientsService.getByFullName(fullName);
    }

    public List<Patient> getAllByDistrict(District district) {
        return patientsService.getAllByDistrict(district);
    }
}
