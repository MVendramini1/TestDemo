package org.ex1.demo.controller.degree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DegreeDTO {
    private String university;
    private int year;
}
