package com.senac.viverbem.domain.user;

public record UserRequestDTO(String firstname, String lastname, String dateofbirth, String email, String password, String gender, String cpf, String phone, String medications, String emergencycontact, String street, String postal_code, String neighborhood, String city, String state, String country) {
}
