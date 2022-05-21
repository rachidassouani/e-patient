package io.rachidassouani.patientApp.service;

import io.rachidassouani.patientApp.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PatientService {

    Patient save(Patient patient);
    Page<Patient> findAll(int page, int size);
    Page<Patient> findAllByFullName(String fullName, int page, int size);
}
