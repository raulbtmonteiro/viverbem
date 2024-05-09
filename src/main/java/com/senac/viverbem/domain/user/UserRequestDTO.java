package com.senac.viverbem.domain.user;

public record UserRequestDTO(String firstname, String lastname, String dateofbirth, String email, String gender, Long address) {
}
