package ru.qnocks.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.qnocks.domain.Patient;
import ru.qnocks.domain.enums.District;
import ru.qnocks.repositories.PatientsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientsService {
    private final PatientsRepository patientsRepository;

    public List<Patient> getALl() {
        return (List<Patient>) patientsRepository.findAll();
    }

    public Patient getById(Long id) {
        return patientsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find Doctor with id " + id));
    }

    public Patient save(Patient patient) {
        return patientsRepository.save(patient);
    }

    public Patient update(Long id, Patient patient) {
        Patient existingPatient = getById(id);
        BeanUtils.copyProperties(patient, existingPatient, "id");
        patientsRepository.save(existingPatient);
        return existingPatient;
    }

    public void delete(Long id) {
        patientsRepository.deleteById(id);
    }

    public Patient getByFullName(String fullName) {
        return patientsRepository.findByFullName(fullName);
    }

    public List<Patient> getAllByDistrict(District district) {
        return patientsRepository.findAllByDistrict(district);
    }
}
