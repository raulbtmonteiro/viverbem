package com.senac.viverbem.domain.activity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.senac.viverbem.domain.address.AddressModel;
import com.senac.viverbem.domain.user.UserModel;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "activities")
@Entity(name = "activities")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
public class ActivityModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String datetime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "local", referencedColumnName = "id")
    private AddressModel local;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "owner", referencedColumnName = "id")
    private UserModel owner;

    public ActivityModel(ActivityRequestDTO data, AddressModel local, UserModel owner) {
        this.title = data.title();
        this.description = data.description();
        this.datetime = data.datetime();
        this.local = local;
        this.owner = owner;
    }
}
