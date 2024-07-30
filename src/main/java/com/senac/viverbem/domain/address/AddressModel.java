package com.senac.viverbem.domain.address;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "addresses")
@Entity(name = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class AddressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String postal_code;
    private String neighborhood;
    private String city;
    private String state;
    private String country;

}
