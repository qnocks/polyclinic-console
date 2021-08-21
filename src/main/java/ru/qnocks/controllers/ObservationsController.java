package ru.qnocks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.qnocks.domain.Observation;
import ru.qnocks.domain.Patient;
import ru.qnocks.enums.District;
import ru.qnocks.services.ObservationsService;

import java.util.List;

@Component
public class ObservationsController {
    private final ObservationsService observationsService;

    @Autowired
    public ObservationsController(ObservationsService observationsService) {
        this.observationsService = observationsService;
    }

    public List<Observation> getAll() {
        return observationsService.getALl();
    }

    public List<Observation> getAllByPatient(Patient patient) {
        return observationsService.getALlByPatient(patient);
    }

    public Observation get(Long id) {
        return observationsService.getById(id);
    }

    public void save(Observation observation) {
        observationsService.save(observation);
    }

    public void update(Long id, Observation observation) {
        observationsService.update(id, observation);
    }

    public void delete(Long id) {
        observationsService.delete(id);
    }

    public int countByPatientDistrict(District district) {
        return observationsService.countByPatientDistrict(district);
    }

    public int countByMonth(String month) {
        return observationsService.countByMonth(month);
    }



}
