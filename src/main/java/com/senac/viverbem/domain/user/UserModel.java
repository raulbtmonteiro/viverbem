package com.senac.viverbem.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.senac.viverbem.domain.activity.ActivityModel;
import com.senac.viverbem.domain.address.AddressModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "users")
@Entity(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String dateofbirth;
    private String email;
    private String gender;
    private String cpf;
    private String phone;
    private String medications;
    private String emergencycontact;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address", referencedColumnName = "id")
    private AddressModel address;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ActivityModel> activities;

    public UserModel(UserRequestDTO data, AddressModel address){
        this.firstname = data.firstname();
        this.lastname = data.lastname();
        this.email = data.email();
        this.dateofbirth = data.dateofbirth();
        this.gender = data.gender();
        this.cpf = data.cpf();
        this.phone = data.phone();
        this.medications = data.medications();
        this.emergencycontact = data.emergencycontact();
        this.address = address;
    }

}
