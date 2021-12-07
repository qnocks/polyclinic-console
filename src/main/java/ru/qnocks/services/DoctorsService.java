package ru.qnocks.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.qnocks.domain.Doctor;
import ru.qnocks.repositories.DoctorsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorsService {
    private final DoctorsRepository doctorsRepository;

    public List<Doctor> getALl() {
        return (List<Doctor>) doctorsRepository.findAll();
    }

    public Doctor getById(Long id) {
        return doctorsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find Doctor with id " + id));
    }

    public Doctor save(Doctor doctor) {
        return doctorsRepository.save(doctor);
    }

    public Doctor update(Long id, Doctor doctor) {
        Doctor existingDoctor = getById(id);
        BeanUtils.copyProperties(doctor, existingDoctor, "id");
        doctorsRepository.save(existingDoctor);
        return existingDoctor;
    }

    public void delete(Long id) {
        doctorsRepository.deleteById(id);
    }
}
