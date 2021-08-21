package ru.qnocks.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.qnocks.domain.Observation;
import ru.qnocks.domain.Patient;
import ru.qnocks.enums.District;
import ru.qnocks.repositories.ObservationsRepository;

import java.util.List;

@Service
public class ObservationsService {

    private final ObservationsRepository observationsRepository;

    @Autowired
    public ObservationsService(ObservationsRepository observationsRepository) {
        this.observationsRepository = observationsRepository;
    }

    public List<Observation> getALl() {
        return (List<Observation>) observationsRepository.findAll();
    }

    public List<Observation> getALlByPatient(Patient patient) {
        return observationsRepository.findAllByPatient(patient);
    }

    public Observation getById(Long id) {
        return observationsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find Doctor with id " + id));
    }

    public Observation save(Observation observation) {
        return observationsRepository.save(observation);
    }

    public Observation update(Long id, Observation observation) {
        Observation existingObservation = getById(id);
        BeanUtils.copyProperties(observation, existingObservation, "id");
        observationsRepository.save(existingObservation);
        return existingObservation;
    }

    public void delete(Long id) {
        observationsRepository.deleteById(id);
    }

    public int countByPatientDistrict(District district) {
        return observationsRepository.countByPatientDistrict(district);
    }

    public int countByMonth(String month) {
        return observationsRepository.countByMonth(Integer.parseInt(month));
    }
}
