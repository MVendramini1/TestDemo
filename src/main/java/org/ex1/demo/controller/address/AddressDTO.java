package org.ex1.demo.controller.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AddressDTO {
    @NotBlank(message = "Address Street must not be blank")
    private String street;
    @NotBlank(message = "Address Number must not be blank")
    @Positive
    private String number;
    @NotBlank(message = "Address Country must not be blank")
    private String country;

}
