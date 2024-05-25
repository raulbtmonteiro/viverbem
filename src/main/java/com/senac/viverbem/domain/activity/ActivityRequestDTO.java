package com.senac.viverbem.domain.activity;

public record ActivityRequestDTO(String title, String description, String datetime, Long owner, String street, String postal_code, String neighborhood, String city, String state, String country) {
}
