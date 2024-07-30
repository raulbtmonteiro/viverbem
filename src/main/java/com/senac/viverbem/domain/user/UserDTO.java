package com.senac.viverbem.domain.user;

import com.senac.viverbem.domain.address.AddressDTO;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
    private AddressDTO address;
}
