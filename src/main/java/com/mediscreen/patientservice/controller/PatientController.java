package com.mediscreen.patientservice.controller;

import com.jsoniter.output.JsonStream;
import com.mediscreen.patientservice.service.PatientService;
import com.mediscreen.patientservice.dto.PatientDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public String getAllPatient() {
        log.info("Get all patients");
            return JsonStream.serialize(patientService.getAllPatients());
    }

    @GetMapping("/{id}")
    public String getPatient(@PathVariable("id") Long id) {
        log.info("Get patient by id {}", id);
        try {
            return JsonStream.serialize(patientService.getPatient(id).get());
        } catch (NoSuchElementException e) {
            log.error(e.getMessage());
            return e.getMessage();
        }
    }

    @PutMapping("/{id}")
    public String updatePatient(@PathVariable("id") Long id, @RequestBody PatientDto patient) {
        log.info("Update patient by id : ", id);
        try {
            return JsonStream.serialize(patientService.updatePatient(id, patient));
        } catch (NoSuchElementException e) {
            log.error(e.getMessage());
            return e.getMessage();
        } catch (Exception e) {
            log.error(e.getMessage());
            return e.getMessage();
        }
    }

    @PostMapping("/add")
    public String addPatient(@RequestBody PatientDto patientDto) {
        log.info("Add new patient");
        try {
            return JsonStream.serialize(patientService.addPatient(patientDto));
        } catch (Exception e) {
            log.error(e.getMessage());
            return e.getMessage();
        }
    }

}