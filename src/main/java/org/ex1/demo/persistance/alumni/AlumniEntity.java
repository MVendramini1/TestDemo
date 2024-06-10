package org.ex1.demo.persistance.alumni;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;
import org.ex1.demo.persistance.address.AddressEntity;
import org.ex1.demo.persistance.education.EducationEntity;

@Data
@Entity
@Table(name = "Alumni")
public class AlumniEntity {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true, nullable = false)
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<AddressEntity> addresses;
    @OneToOne(cascade = CascadeType.ALL)
    private EducationEntity education;
}
