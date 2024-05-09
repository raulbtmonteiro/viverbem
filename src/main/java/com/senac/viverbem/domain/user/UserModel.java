package com.senac.viverbem.domain.user;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "users")
@Entity(name = "users")
@Data
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

    public UserModel(UserRequestDTO data){
        this.firstname = data.firstname();
        this.lastname = data.lastname();
        this.email = data.email();
        this.dateofbirth = data.dateofbirth();
        this.gender = data.gender();
        this.address = data.address();
    }

}
