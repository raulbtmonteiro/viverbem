package com.senac.viverbem.domain.address;

import com.senac.viverbem.domain.user.UserRequestDTO;

public record AddressRequestDTO(String street, String postal_code, String neighborhood, String city, String state, String country) {
    public AddressRequestDTO(UserRequestDTO data){
        this(data.street(),data.postal_code(),data.neighborhood(),data.city(),data.state(),data.country());
    }
}
