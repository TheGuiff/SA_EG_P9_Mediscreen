package com.mediscreen.patientservice.dto;

import com.mediscreen.patientservice.model.Patient;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PatientDto {

    private Long patientId;
    private String firstname;
    private String lastname;
    private String birthdate;
    private String gender;
    private String address;
    private String phone;

    public PatientDto() {

    }

    public PatientDto(Patient patient) {
        this.patientId = patient.getPatientId();
        this.firstname = patient.getFirstname();
        this.lastname = patient.getLastname();
        this.birthdate = patient.getBirthdate().toString();
        this.gender = patient.getGender().toString();
        this.address = patient.getAddress();
        this.phone = patient.getPhone();
    }

    public PatientDto(String firstname,
                      String lastname,
                      String birthdate,
                      String gender,
                      String address,
                      String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
    }
}
