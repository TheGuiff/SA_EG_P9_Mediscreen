package com.mediscreen.patientservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mediscreen.patientservice.dto.PatientDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public Patient (String firstnameIn,
                    String lastnameIn,
                    LocalDate birthdateIn,
                    Gender genderIn,
                    String addressIn,
                    String phoneIn) {
        this.setFirstname(firstnameIn);
        this.setLastname(lastnameIn);
        this.setBirthdate(birthdateIn);
        this.setGender(genderIn);
        this.setAddress(addressIn);
        this.setPhone(phoneIn);
    }

    public Patient (PatientDto patientDto) {
        this.firstname = patientDto.getFirstname();
        this.lastname = patientDto.getLastname();
        this.birthdate = LocalDate.parse(patientDto.getBirthdate());
        this.gender = Gender.valueOf(patientDto.getGender());
        this.address = patientDto.getAddress();
        this.phone = patientDto.getPhone();
    }

    public Patient() {
    }

}
