package com.mediscreen.patientservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mediscreen.patientservice.dto.PatientDto;
import com.mediscreen.patientservice.exceptions.GenderConvertException;
import com.mediscreen.patientservice.exceptions.LocalDateConverterException;
import com.mediscreen.patientservice.exceptions.MandatoryFieldsException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name="patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long patientId;

    @Column
    @NotBlank(message = "Firstname is mandatory")
    private String firstname;

    @Column
    @NotBlank(message = "lastname is mandatory")
    private String lastname;

    @Column
    @NotNull(message = "Birthdate is mandatory")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate birthdate;

    @Column
    @NotNull(message = "Gender is mandatory")
    private Gender gender;

    @Column
    private String address;

    @Column
    private String phone;

    public Patient (PatientDto patientDto) throws Exception {
        if (patientDto.getFirstname().isEmpty() || patientDto.getLastname().isEmpty() || patientDto.getBirthdate().isEmpty() || patientDto.getGender().isEmpty()
        || patientDto.getFirstname().isBlank() || patientDto.getLastname().isBlank() || patientDto.getBirthdate().isBlank() || patientDto.getGender().isBlank()) {
            throw new MandatoryFieldsException("Missing data : firstname, lastname, birthdate and gender are mandatory");
        }
        this.firstname = patientDto.getFirstname();
        this.lastname = patientDto.getLastname();
        try {
            this.birthdate = LocalDate.parse(patientDto.getBirthdate());
        } catch (Exception e) {
            throw new LocalDateConverterException("The birthdate is not a date - expected format : yyyy-mm-dd");
        }
        try {
            this.gender = Gender.valueOf(patientDto.getGender());
        } catch (Exception e) {
            throw new GenderConvertException("Unknown gender - must be F(female) or M(male)");
        }
        this.address = patientDto.getAddress();
        this.phone = patientDto.getPhone();
    }

    public Patient() {
    }

}
