package com.mediscreen.patientservice;

import com.mediscreen.patientservice.dto.PatientDto;
import com.mediscreen.patientservice.exceptions.GenderConvertException;
import com.mediscreen.patientservice.exceptions.LocalDateConverterException;
import com.mediscreen.patientservice.exceptions.MandatoryFieldsException;
import com.mediscreen.patientservice.model.Patient;
import java.util.List;

import com.mediscreen.patientservice.repository.PatientRepository;
import com.mediscreen.patientservice.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PatientServiceTest {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    PatientService patientService;

    private final PatientDto patientDto1 = new PatientDto("test","ok1","1998-01-01","M","12 Elm st","555-55-55");
    private final PatientDto patientDto2 = new PatientDto("test","ok2","1998-01-01","M","12 Elm st","555-55-55");
    private final PatientDto patientDto3 = new PatientDto("test","ok3","1998-01-01","M","12 Elm st","555-55-55");
    private final PatientDto patientDto4 = new PatientDto("test","ko1","turlututu","M","12 Elm st","555-55-55");
    private final PatientDto patientDto5 = new PatientDto("test","ko2","1998-01-01","P","12 Elm st","555-55-55");
    private final PatientDto patientDto6 = new PatientDto("test","ko3","","M","12 Elm st","555-55-55");

    private static Long id1;
    private static Long id2;

    @BeforeEach
    public void testBase() throws Exception{
        patientRepository.deleteAll();
        Patient patient1 = patientRepository.save(new Patient(patientDto1));
        id1 = patient1.getPatientId();
        Patient patient2 = patientRepository.save(new Patient(patientDto2));
        id2 = patient2.getPatientId();
    }

    @Test
    public void getPatientOk() {
        PatientDto patientDto = patientService.getPatient(id1);
        assertEquals("ok1", patientDto.getLastname());
    }

    @Test
    public void getAllPatientOk() {
        List<PatientDto> patientDtoList = patientService.getAllPatients();
        assertEquals(2,patientDtoList.size());
    }

    @Test
    public void updatePatientOk() throws Exception {
        PatientDto patientDto = patientService.updatePatient(id1, patientDto3);
        assertEquals(2, patientRepository.findAll().size());
        Optional<Patient> optionalPatient = patientRepository.findById(id1);
        assertTrue(optionalPatient.isPresent());
        assertEquals("ok3", optionalPatient.get().getLastname());
    }

    @Test
    public void addPatientOk() throws Exception {
        PatientDto patientDto = patientService.addPatient(patientDto3);
        assertEquals(3, patientRepository.findAll().size());
    }

    @Test
    public void addPatientKoOnBadBirthdate() throws Exception {
        assertThrows(LocalDateConverterException.class, () -> patientService.addPatient(patientDto4));
    }

    @Test
    public void addPatientKoOnBadGender() throws Exception {
        assertThrows(GenderConvertException.class, () -> patientService.addPatient(patientDto5));
    }

    @Test
    public void addPatientKoOnMissingMandatory() throws Exception {
        assertThrows(MandatoryFieldsException.class, () -> patientService.addPatient(patientDto6));
    }

    @Test
    public void updatePatientKoOnBadBirthdate() throws Exception {
        assertThrows(LocalDateConverterException.class, () -> patientService.updatePatient(id1, patientDto4));
    }

    @Test
    public void updatePatientKoOnBadGender() throws Exception {
        assertThrows(GenderConvertException.class, () -> patientService.updatePatient(id1, patientDto5));
    }

    @Test
    public void updatePatientKoOnMissingMandatory() throws Exception {
        assertThrows(MandatoryFieldsException.class, () -> patientService.updatePatient(id1, patientDto6));
    }
}
