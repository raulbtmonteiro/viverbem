package com.senac.viverbem.domain.user.dto;

import com.senac.viverbem.domain.address.dto.AddressDTO;
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
    private String gender;
    private String cpf;
    private String phone;
    private String medications;
    private String emergencycontact;
    private AddressDTO address;

    public UserDTO(UserPostDTO data){
        this.id = data.getId();
        this.firstname = data.getFirstname();
        this.lastname = data.getLastname();
        this.dateofbirth = data.getDateofbirth();
        this.email = data.getEmail();
        this.gender = data.getGender();
        this.phone = data.getPhone();
        this.medications = data.getMedications();
        this.emergencycontact = data.getEmergencycontact();
        this.address = data.getAddress();
    }
}
