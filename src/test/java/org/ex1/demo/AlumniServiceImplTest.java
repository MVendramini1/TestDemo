package org.ex1.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.ex1.demo.controller.alumni.AlumniPostDTO;
import org.ex1.demo.controller.degree.DegreeDTO;
import org.ex1.demo.controller.education.EducationDTO;
import org.ex1.demo.core.alumni.AlumniMapper;
import org.ex1.demo.core.alumni.AlumniServiceImpl;
import org.ex1.demo.persistance.alumni.AlumniEntity;
import org.ex1.demo.persistance.alumni.AlumniRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AlumniServiceImplTest {

    @Mock
    private AlumniRepository alumniRepository;

    @Mock
    private AlumniMapper alumniMapper;

    @InjectMocks
    private AlumniServiceImpl alumniService;

    private AlumniPostDTO alumniPostDTO;
    private EducationDTO educationDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        educationDTO = new EducationDTO();
        educationDTO.setPhd(new DegreeDTO("UCSD", 2009));
        educationDTO.setMaster(new DegreeDTO("Politecnico Milano", 2004));

        alumniPostDTO = new AlumniPostDTO();
        alumniPostDTO.setName("John Doe");
        alumniPostDTO.setEducation(educationDTO);
    }

    @Test
    void testSave() {
        when(alumniMapper.mapDtoToEntity(any(AlumniPostDTO.class))).thenReturn(new AlumniEntity());
        when(alumniRepository.save(any(AlumniEntity.class))).thenReturn(new AlumniEntity());
        when(alumniMapper.mapEntityToDto(any(AlumniEntity.class))).thenReturn(alumniPostDTO);

        AlumniPostDTO result = alumniService.save(alumniPostDTO);

        Assertions.assertEquals("John Doe", result.getName());
        Assertions.assertEquals("UCSD", result.getEducation().getPhd().getUniversity());
    }

    @Test
    void testFindByName_WithNoDegree() {
        when(alumniRepository.findByName("John")).thenReturn(List.of(new AlumniEntity()));
        when(alumniMapper.mapEntityListToDtoList(any(List.class))).thenReturn(
            Collections.singletonList(alumniPostDTO));

        List<AlumniPostDTO> result = alumniService.findByName("John", null);

        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getName());
    }

    @Test
    void testFindByName_WithPhdDegree() {
        when(alumniRepository.findByName("John")).thenReturn(List.of(new AlumniEntity()));
        when(alumniMapper.mapEntityListToDtoList(any(List.class))).thenReturn(
            Collections.singletonList(alumniPostDTO));

        List<AlumniPostDTO> result = alumniService.findByName("John", "phd");

        assertEquals(0, result.size()); // Expecting 0 because of filter logic
    }

    @Test
    void testFindByName_WithMasterDegree() {
        when(alumniRepository.findByName("John")).thenReturn(Arrays.asList(new AlumniEntity()));
        when(alumniMapper.mapEntityListToDtoList(any(List.class))).thenReturn(Arrays.asList(alumniPostDTO));

        List<AlumniPostDTO> result = alumniService.findByName("John", "master");

        assertEquals(0, result.size()); // Expecting 0 because of filter logic
    }

    @Test
    void testFindByName_WithInvalidDegree() {
        List<AlumniPostDTO> result = alumniService.findByName("John", "invalid");

        assertEquals(0, result.size()); // Expecting empty list because of invalid degree
    }
}