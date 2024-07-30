package com.senac.viverbem.mappers.impl;

import com.senac.viverbem.domain.activity.dto.ActivityDTO;
import com.senac.viverbem.domain.activity.ActivityModel;
import com.senac.viverbem.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ActivityMapper implements Mapper<ActivityModel, ActivityDTO> {

    private ModelMapper modelMapper;

    public ActivityMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Override
    public ActivityDTO mapTo(ActivityModel activityModel) {
        return modelMapper.map(activityModel, ActivityDTO.class);
    }

    @Override
    public ActivityModel mapFrom(ActivityDTO activityDTO) {
        return modelMapper.map(activityDTO, ActivityModel.class);
    }
}
