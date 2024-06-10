package org.ex1.demo.controller.alumni;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import lombok.Data;
import org.ex1.demo.controller.address.AddressDTO;
import org.ex1.demo.controller.education.EducationDTO;

@Data
public class AlumniPostDTO {
    @Pattern(regexp = "[A-Z,a-z]+")
    private String name;
    @NotEmpty
    private List<@Valid AddressDTO> addresses;
    private EducationDTO education;
}
