package com.senac.viverbem.domain.activity;

public record ActivityRequestDTO(String title, String description, String datetime, Long local, Long owner) {
}
