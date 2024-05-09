package com.senac.viverbem.domain.address;

public record AddressRequestDTO(String street, String postal_code, String neighborhood, String city, String state, String country) {
}
