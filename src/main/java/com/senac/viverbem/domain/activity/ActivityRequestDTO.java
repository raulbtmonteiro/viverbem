package com.senac.viverbem.domain.activity;

import com.senac.viverbem.domain.user.UserModel;

public record ActivityRequestDTO(String title, String description, String datetime, UserModel owner, String street, String postal_code, String neighborhood, String city, String state, String country) {
}
