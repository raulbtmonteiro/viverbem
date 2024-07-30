package com.senac.viverbem.domain.activity.dto;

import com.senac.viverbem.domain.address.dto.AddressDTO;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ActivityPostDTO {

    private Long id;
    private String title;
    private String description;
    private String datetime;
    private AddressDTO local;
    private Long owner;
}
