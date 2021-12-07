package ru.qnocks.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.qnocks.domain.Patient;
import ru.qnocks.domain.enums.District;

import java.util.List;

@Repository
public interface PatientsRepository extends CrudRepository<Patient, Long> {

    Patient findByFullName(String fullName);

    List<Patient> findAllByDistrict(District district);
}
