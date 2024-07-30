package com.senac.viverbem.domain.address.dto;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressDTO {

    private Long id;
    private String street;
    private String postal_code;
    private String neighborhood;
    private String city;
    private String state;
    private String country;
}
