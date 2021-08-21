package ru.qnocks.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.qnocks.domain.Doctor;

@Repository
public interface DoctorsRepository extends CrudRepository<Doctor, Long> {
}
