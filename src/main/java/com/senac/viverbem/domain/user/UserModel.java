package com.senac.viverbem.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "users")
@Entity(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String dateofbirth;
    private String email;
    private String gender;
    private Long address;

    public UserModel(UserRequestDTO data, Long address){
        this.firstname = data.firstname();
        this.lastname = data.lastname();
        this.email = data.email();
        this.dateofbirth = data.dateofbirth();
        this.gender = data.gender();
        this.address = address;
    }

}
