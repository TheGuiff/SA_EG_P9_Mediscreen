package com.mediscreen.patientservice;

import com.jsoniter.output.JsonStream;
import com.mediscreen.patientservice.dto.PatientDto;
import com.mediscreen.patientservice.model.Patient;
import com.mediscreen.patientservice.controller.PatientController;
import com.mediscreen.patientservice.repository.PatientRepository;
import com.mediscreen.patientservice.service.PatientService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PatientControllerTest {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    PatientController patientController;

    @MockBean
    PatientService patientService;

    private final PatientDto patientDto1 = new PatientDto("test","ok1","1998-01-01","M","12 Elm st","555-55-55");
    private final PatientDto patientDto2 = new PatientDto("test","ok2","1998-01-01","M","12 Elm st","555-55-55");
    private final PatientDto patientDto3 = new PatientDto("test","ok3","1998-01-01","M","12 Elm st","555-55-55");
    private final PatientDto patientDto4 = new PatientDto("test","ko1","turlututu","M","12 Elm st","555-55-55");
    private final PatientDto patientDto5 = new PatientDto("test","ko2","1998-01-01","P","12 Elm st","555-55-55");
    private final PatientDto patientDto6 = new PatientDto("test","ko3","","M","12 Elm st","555-55-55");

    private static Long id1;
    private static Long id2;
    private static List<PatientDto> patientDtoList = new ArrayList<>();
    private static String listPatientsDto;
    private static String patientDtoJson;

    @BeforeAll
    public void init() {
        patientDtoList.add(patientDto1);
        patientDtoList.add(patientDto2);
        listPatientsDto = JsonStream.serialize(patientDtoList);
        patientDtoJson  = JsonStream.serialize(patientDto1);
    }

    @BeforeEach
    public void testBase() throws Exception{
        patientRepository.deleteAll();
        Patient patient1 = patientRepository.save(new Patient(patientDto1));
        id1 = patient1.getPatientId();
        Patient patient2 = patientRepository.save(new Patient(patientDto2));
        id2 = patient2.getPatientId();
    }

    @Test
    public void getAllPatients() {
        when(patientService.getAllPatients()).thenReturn(patientDtoList);
        assertEquals(listPatientsDto, patientController.getAllPatient());
    }

    @Test
    public void getPatient() {
        when(patientService.getPatient(isA(Long.class))).thenReturn(patientDto1);
        assertEquals(patientDtoJson, patientController.getPatient(1L));
    }

}
