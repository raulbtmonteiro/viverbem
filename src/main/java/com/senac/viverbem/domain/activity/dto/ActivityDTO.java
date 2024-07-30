package com.senac.viverbem.domain.activity.dto;


import com.senac.viverbem.domain.address.dto.AddressDTO;
import com.senac.viverbem.domain.user.dto.UserDTO;
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

    public ActivityDTO(ActivityPostDTO data, UserDTO owner){
        this.id = data.getId();
        this.title = data.getTitle();
        this.description = data.getDescription();
        this.datetime = data.getDatetime();
        this.local = data.getLocal();
        this.owner = owner;
    }

}

