package com.mediscreen.patientservice.dto;

import com.mediscreen.patientservice.model.Gender;
import com.mediscreen.patientservice.model.Patient;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PatientDto {

    private String firstname;
    private String lastname;
    private String birthdate;
    private String gender;
    private String address;
    private String phone;

    public PatientDto() {

    }

    public PatientDto(Patient patient) {
        this.firstname = patient.getFirstname();
        this.lastname = patient.getLastname();
        this.birthdate = patient.getBirthdate().toString();
        this.gender = patient.getGender().toString();
        this.address = patient.getAddress();
        this.phone = patient.getPhone();
    }
}
