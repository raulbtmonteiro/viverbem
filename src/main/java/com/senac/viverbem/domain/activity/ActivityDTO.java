package com.senac.viverbem.domain.activity;


import com.senac.viverbem.domain.address.AddressDTO;
import com.senac.viverbem.domain.user.UserDTO;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ActivityDTO {

    private Long id;
    private String title;
    private String description;
    private String datetime;
    private AddressDTO local;
    private UserDTO owner;
}

