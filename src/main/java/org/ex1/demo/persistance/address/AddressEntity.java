package org.ex1.demo.persistance.address;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Address")
public class AddressEntity {

    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String street;
    @Column
    private String number;
    @Column
    private String country;
}
