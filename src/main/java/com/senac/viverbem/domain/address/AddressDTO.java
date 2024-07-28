package com.senac.viverbem.domain.address;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private Long id;
    private String street;
    private String postal_code;
    private String neighborhood;
    private String city;
    private String state;
    private String country;
}
