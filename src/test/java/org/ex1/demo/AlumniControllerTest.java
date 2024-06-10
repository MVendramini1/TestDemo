package org.ex1.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.ex1.demo.controller.alumni.AlumniController;
import org.ex1.demo.controller.alumni.AlumniGetDTO;
import org.ex1.demo.controller.alumni.AlumniPostDTO;
import org.ex1.demo.controller.degree.DegreeDTO;
import org.ex1.demo.controller.education.EducationDTO;
import org.ex1.demo.core.alumni.AlumniService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AlumniControllerTest {

    @Mock
    private AlumniService alumniService;

    @InjectMocks
    private AlumniController alumniController;

    private AlumniPostDTO alumniPostDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        alumniPostDTO = new AlumniPostDTO();
        alumniPostDTO.setName("John Doe");
        alumniPostDTO.setEducation(new EducationDTO());
        alumniPostDTO.getEducation().setPhd(new DegreeDTO());
    }

    @Test
    void testFindByName_WithResults() {
        List<AlumniPostDTO> alumniPostDTOList = Arrays.asList(alumniPostDTO);
        when(alumniService.findByName("John", "PhD")).thenReturn(alumniPostDTOList);

        ResponseEntity<AlumniGetDTO> response = alumniController.findByName("John", "PhD");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getTotalCount());
        assertEquals("John Doe", response.getBody().getData().get(0).getName());
    }

    @Test
    void testFindByName_NoResults() {
        when(alumniService.findByName("John", "PhD")).thenReturn(Collections.emptyList());

        ResponseEntity<AlumniGetDTO> response = alumniController.findByName("John", "PhD");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testInsert() {
        when(alumniService.save(any(AlumniPostDTO.class))).thenReturn(alumniPostDTO);

        ResponseEntity<AlumniPostDTO> response = alumniController.insert(alumniPostDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("John Doe", response.getBody().getName());
        assertNotNull(response.getBody().getEducation().getPhd());
    }
}
