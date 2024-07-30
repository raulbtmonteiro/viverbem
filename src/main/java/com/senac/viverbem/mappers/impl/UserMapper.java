package com.senac.viverbem.mappers.impl;

import com.senac.viverbem.domain.user.dto.UserDTO;
import com.senac.viverbem.domain.user.UserModel;
import com.senac.viverbem.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<UserModel, UserDTO> {

    private ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTO mapTo(UserModel userModel) {
        return modelMapper.map(userModel, UserDTO.class);
    }

    @Override
    public UserModel mapFrom(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserModel.class);
    }
}
