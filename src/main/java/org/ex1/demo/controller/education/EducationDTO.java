package org.ex1.demo.controller.education;

import lombok.Data;
import org.ex1.demo.controller.degree.DegreeDTO;

@Data
public class EducationDTO {
    private DegreeDTO master;
    private DegreeDTO phd;

}
