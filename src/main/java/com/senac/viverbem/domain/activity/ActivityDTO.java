package com.senac.viverbem.domain.activity;


import com.senac.viverbem.domain.address.AddressModel;
import com.senac.viverbem.domain.user.UserModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDTO {

    private Long id;
    private String title;
    private String description;
    private String datetime;
    private AddressModel local;
    private UserModel owner;
}

