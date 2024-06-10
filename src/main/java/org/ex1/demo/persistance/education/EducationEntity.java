package org.ex1.demo.persistance.education;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import org.ex1.demo.persistance.degree.DegreeEntity;

@Data
@Entity
@Table(name = "Education")
public class EducationEntity {
    @Id
    @GeneratedValue
    private Integer id;
    @OneToOne(cascade = CascadeType.ALL)
    private DegreeEntity master;
    @OneToOne(cascade = CascadeType.ALL)
    private DegreeEntity phd;
}
