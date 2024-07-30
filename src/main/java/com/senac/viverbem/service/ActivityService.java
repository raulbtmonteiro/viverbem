package com.senac.viverbem.service;

import com.senac.viverbem.domain.activity.ActivityDTO;
import com.senac.viverbem.domain.activity.ActivityModel;
import com.senac.viverbem.domain.activity.ActivityRepository;
import com.senac.viverbem.domain.address.AddressDTO;
import com.senac.viverbem.domain.user.UserDTO;
import com.senac.viverbem.mappers.impl.ActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository repository;
    private final ActivityMapper activityMapper;
    private final UserService userService;
    private final AddressService addressService;

    public ActivityService(AddressService addressService, UserService userService, ActivityMapper activityMapper) {
        this.addressService = addressService;
        this.userService = userService;
        this.activityMapper = activityMapper;
    }

    @Transactional(readOnly = true)
    public List<ActivityDTO> getAllActivities() {
        List<ActivityModel> activities = repository.findAll();
        return activities.stream().map(activityMapper::mapTo).toList();
    }

    @Transactional(readOnly = true)
    public Optional<ActivityDTO> getActivityById(Long id) {
        Optional<ActivityModel> activity = repository.findById(id);
        if(activity.isPresent()){
            ActivityDTO activityDto = activityMapper.mapTo(activity.get());
            return Optional.ofNullable(activityDto);
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    public ActivityDTO saveActivity(ActivityDTO data) {
        UserDTO savedUser = userService.saveUser(data.getOwner());
        data.setOwner(savedUser);
        AddressDTO savedAddress = addressService.createAddress(data.getLocal());
        data.setLocal(savedAddress);
        ActivityModel activityToSave = activityMapper.mapFrom(data);
        ActivityModel activity = repository.save(activityToSave);
        return activityMapper.mapTo(activity);
    }

    @Transactional
    public void deleteActivity(Long id) { repository.deleteById(id); }

//    public ActivityDTO partialUptade(String path, String value, ActivityDTO activity){
//        switch (path){
//            case "title":
//                activity.setTitle(value);
//                break;
//            case "description":
//                activity.setDescription(value);
//                break;
//            case "datetime":
//                activity.setDatetime(value);
//                break;
//            case "owner":
//                Optional<UserDTO> newowner = userService.getUserById(Long.parseLong(value));
//                if(newowner.isPresent()){
//                    activity.setOwner(newowner.get());
//                }
//                break;
//            default:
//                break;
//        }
//        return activity;
//    }
}
