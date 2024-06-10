package org.ex1.demo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.Collections;
import org.ex1.demo.controller.address.AddressDTO;
import org.ex1.demo.controller.alumni.AlumniPostDTO;
import org.ex1.demo.controller.degree.DegreeDTO;
import org.ex1.demo.controller.education.EducationDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class AlumniIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private AlumniPostDTO alumniPostDTO;

    private AlumniPostDTO alumniPostDTO2;

    @BeforeEach
    void setUp() {
        EducationDTO educationDTO = new EducationDTO();
        educationDTO.setPhd(new DegreeDTO("UCSD", 2009));
        educationDTO.setMaster(new DegreeDTO("Politecnico Milano", 2004));
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setCountry("country");
        addressDTO.setNumber("22");
        addressDTO.setStreet("street");

        alumniPostDTO = new AlumniPostDTO();
        alumniPostDTO.setName("JohnDoe");
        alumniPostDTO.setEducation(educationDTO);
        alumniPostDTO.setAddresses(Collections.singletonList(addressDTO));

        alumniPostDTO2 = new AlumniPostDTO();
        alumniPostDTO2.setName("JohnDoeee");
        alumniPostDTO2.setEducation(educationDTO);
        alumniPostDTO2.setAddresses(Collections.singletonList(addressDTO));

    }

    @Test
    void testFindByName_WithResults() throws Exception {
        // Insert the alumni to find later
        mockMvc.perform(post("/alumni")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(alumniPostDTO)))
            .andExpect(status().isCreated());

        // Test finding by name
        mockMvc.perform(get("/alumni")
                .param("name", "JohnDoe"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json("{\"data\":[{\"name\":\"JohnDoe\",\"addresses\":[{\"street\":\"street\",\"number\":\"22\",\"country\":\"country\"}],\"education\":{\"master\":{\"university\":\"Politecnico Milano\",\"year\":2004},\"phd\":{\"university\":\"UCSD\",\"year\":2009}}}],\"totalCount\":1}"));
    }

    @Test
    void testFindByName_NoResults() throws Exception {
        mockMvc.perform(get("/alumni")
                .param("name", "NonExistentName"))
            .andExpect(status().isNoContent());
    }

    @Test
    void testInsert() throws Exception {
        mockMvc.perform(post("/alumni")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(alumniPostDTO2)))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json("{\"name\":\"JohnDoeee\",\"addresses\":[{\"street\":\"street\",\"number\":\"22\",\"country\":\"country\"}],\"education\":{\"master\":{\"university\":\"Politecnico Milano\",\"year\":2004},\"phd\":{\"university\":\"UCSD\",\"year\":2009}}}"));
    }
}
