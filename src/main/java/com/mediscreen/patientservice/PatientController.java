package com.mediscreen.patientservice;

import com.jsoniter.output.JsonStream;
import com.mediscreen.patientservice.dto.PatientDto;
import com.mediscreen.patientservice.model.Patient;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/")
    public String getAllPatient() {
        log.info("Get all patients");
        return JsonStream.serialize(patientService.getAllPatients());
    }

    @GetMapping("/{id}")
    public String getPatient(@PathVariable("id") Long id) {
        log.info("Get patient by id {}", id);
        return JsonStream.serialize(patientService.getPatient(id).get());
    }

    @PutMapping("/{id}")
    public String updatePatient(@PathVariable("id") Long id, @RequestBody PatientDto patient) {
        log.info("Update patient by id : ", id);
        return JsonStream.serialize(patientService.updatePatient(id, patient));
    }

    @PostMapping("/add")
    public String addPatient(@RequestBody PatientDto patientDto) {
        log.info("Add patient");
        return JsonStream.serialize(patientService.addPatient(patientDto));
    }

}