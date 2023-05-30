package com.mediscreen.patientservice.controller;

import com.jsoniter.output.JsonStream;
import com.mediscreen.patientservice.exceptions.NoPatientException;
import com.mediscreen.patientservice.model.Patient;
import com.mediscreen.patientservice.service.PatientService;
import com.mediscreen.patientservice.dto.PatientDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Slf4j
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    /**
     * List of patients
     * @return String
     */
    @GetMapping("/")
    public List<PatientDto> getAllPatient() {
        log.info("Get all patients");
            return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public PatientDto getPatient(@PathVariable("id") Long id) throws NoPatientException {
        log.info("Get patient by id {}", id);
        return patientService.getPatient(id);
    }

    @PutMapping("/{id}")
    public PatientDto updatePatient(@PathVariable("id") Long id, @RequestBody PatientDto patient) throws Exception {
        log.info("Update patient by id : ", id);
        return patientService.updatePatient(id, patient);
    }

    @PostMapping("/add")
    public PatientDto addPatient(@RequestBody PatientDto patientDto) throws Exception {
        log.info("Add new patient");
        return patientService.addPatient(patientDto);
    }

}