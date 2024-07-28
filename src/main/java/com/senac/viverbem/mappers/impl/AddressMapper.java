package com.senac.viverbem.mappers.impl;

import com.senac.viverbem.domain.address.AddressDTO;
import com.senac.viverbem.domain.address.AddressModel;
import com.senac.viverbem.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper implements Mapper<AddressModel, AddressDTO> {

    private ModelMapper modelMapper;

    public AddressMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Override
    public AddressDTO mapTo(AddressModel addressModel) {
        return modelMapper.map(addressModel, AddressDTO.class);
    }

    @Override
    public AddressModel mapFrom(AddressDTO addressDTO) {
        return modelMapper.map(addressDTO, AddressModel.class);
    }
}
