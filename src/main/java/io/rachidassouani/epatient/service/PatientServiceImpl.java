package io.rachidassouani.epatient.service;

import io.rachidassouani.epatient.dao.PatientRepository;
import io.rachidassouani.epatient.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // saving patient
    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    // find all patients by page and size
    @Override
    public Page<Patient> findAll(int page, int size) {
        return patientRepository.findAll(PageRequest.of(page, size));
    }

    // searching for patients by their first or last name
    @Override
    public Page<Patient> findAllByFullName(String fullName, int page, int size) {
        return patientRepository.findByFirstNameOrLastNameContains(fullName, fullName, PageRequest.of(page, size));
    }

    // delete patient by his ID
    @Override
    public void delete(long id) {
        patientRepository.deleteById(id);
    }

    // finding patient by his ID
    @Override
    public Patient findById(long id) {
        return patientRepository.findById(id).get();
    }
}
