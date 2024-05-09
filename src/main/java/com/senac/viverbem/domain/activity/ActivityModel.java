package com.senac.viverbem.domain.activity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "activities")
@Entity(name = "activities")
@Data
public class ActivityModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String datetime;
    private Long local;
    private Long owner;

    public ActivityModel(ActivityRequestDTO data) {
        this.title = data.title();
        this.description = data.description();
        this.datetime = data.datetime();
        this.local = data.local();
        this.owner = data.owner();
    }
}
