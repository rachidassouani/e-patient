package io.rachidassouani.epatient.dao;

import io.rachidassouani.epatient.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // searching for patients by their first or lastname
    Page<Patient> findByFirstNameOrLastNameContains(String fullName, String fullName2, Pageable pageable);
}
