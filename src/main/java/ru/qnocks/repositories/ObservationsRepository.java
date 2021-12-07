package ru.qnocks.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.qnocks.domain.Observation;
import ru.qnocks.domain.Patient;
import ru.qnocks.domain.enums.District;

import java.util.List;

@Repository
public interface ObservationsRepository extends CrudRepository<Observation, Long> {

    List<Observation> findAllByPatient(Patient patient);

    int countByPatientDistrict(District district);

    List<Observation> findAllByPatientDistrict(District district);

    @Query(value = "SELECT COUNT(*) FROM observations WHERE EXTRACT(MONTH FROM observation_time) = :month", nativeQuery = true)
    Integer countByMonth(int month);
}
