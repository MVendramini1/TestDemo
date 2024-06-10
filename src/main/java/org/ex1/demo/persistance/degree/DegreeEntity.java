package org.ex1.demo.persistance.degree;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.IdGeneratorType;

@Data
@Entity
@Table(name = "Degree")
public class DegreeEntity {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String university;
    @Column(name = "degree_year")
    private int year;
}
