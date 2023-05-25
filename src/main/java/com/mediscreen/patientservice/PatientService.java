package com.mediscreen.patientservice;

import com.mediscreen.patientservice.dto.PatientDto;
import com.mediscreen.patientservice.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mediscreen.patientservice.PatientRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Optional<PatientDto> getPatient(Long patientId) {
        return Optional.of(new PatientDto(patientRepository.findById(patientId).get()));
    }

    public List<PatientDto> getAllPatients() {
        List<Patient> patientList = patientRepository.findAll();
        return patientList.stream()
                .map(patient -> new PatientDto(patient))
                .collect(Collectors.toList());
    }

    @Transactional
    public PatientDto updatePatient(Long patientId, PatientDto patientDto) {
        patientRepository.deleteById(patientId);
        Patient patient = new Patient(patientDto);
        patient.setPatientId(patientId);
        return new PatientDto(patientRepository.save(patient));
    }

    @Transactional
    public PatientDto addPatient (PatientDto patientDto) {
        Patient patient = new Patient(patientDto);
        return new PatientDto(patientRepository.save(patient));
    }

}
