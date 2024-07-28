package com.senac.viverbem.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private String dateofbirth;
    private String email;
    private String password;
    private String gender;
    private String cpf;
    private String phone;
    private String medications;
    private String emergencycontact;
}
