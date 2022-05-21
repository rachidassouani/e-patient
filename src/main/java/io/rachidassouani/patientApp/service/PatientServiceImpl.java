package io.rachidassouani.patientApp.service;

import io.rachidassouani.patientApp.dao.PatientRepository;
import io.rachidassouani.patientApp.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Page<Patient> findAll(int page, int size) {
        return patientRepository.findAll(PageRequest.of(page, size));
    }
    @Override
    public Page<Patient> findAllByFullName(String fullName, int page, int size) {
        return patientRepository.findByFirstNameOrLastNameContains(fullName, fullName, PageRequest.of(page, size));
    }
}
