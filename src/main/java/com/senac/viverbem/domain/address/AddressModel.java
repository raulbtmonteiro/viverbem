package com.senac.viverbem.domain.address;

import com.senac.viverbem.domain.user.UserRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "addresses")
@Entity(name = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
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

    public AddressModel(AddressRequestDTO data){
        this.street = data.street();
        this.postal_code = data.postal_code();
        this.neighborhood = data.neighborhood();
        this.city = data.city();
        this.state = data.state();
        this.country = data.country();
    }

    public AddressModel(UserRequestDTO data){
        this.street = data.street();
        this.postal_code = data.postal_code();
        this.neighborhood = data.neighborhood();
        this.city = data.city();
        this.state = data.state();
        this.country = data.country();
    }

}
