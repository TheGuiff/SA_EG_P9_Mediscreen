package com.mediscreen.patientservice.service;

import com.mediscreen.patientservice.dto.PatientDto;
import com.mediscreen.patientservice.model.Patient;
import com.mediscreen.patientservice.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public PatientDto updatePatient(Long patientId, PatientDto patientDto) throws Exception {
        Patient patient = new Patient(patientDto);
        patient.setPatientId(patientId);
        return new PatientDto(patientRepository.save(patient));
    }

    public PatientDto addPatient (PatientDto patientDto) throws Exception {
        Patient patient = new Patient(patientDto);
        return new PatientDto(patientRepository.save(patient));
    }

}
