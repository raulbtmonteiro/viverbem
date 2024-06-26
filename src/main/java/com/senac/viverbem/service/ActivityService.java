package com.senac.viverbem.service;

import com.senac.viverbem.domain.activity.ActivityModel;
import com.senac.viverbem.domain.user.UserModel;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class ActivityService {

    private final UserService userService;

    public ActivityService(UserService userService) {
        this.userService = userService;
    }

    public ActivityModel updateActivity(String path, String value, ActivityModel activity){
        switch (path){
            case "title":
                activity.setTitle(value);
                break;
            case "description":
                activity.setDescription(value);
                break;
            case "datetime":
                activity.setDatetime(value);
                break;
            case "owner":
                Optional<UserModel> newowner = userService.getUser(Long.parseLong(value));
                if(newowner.isPresent()){
                    activity.setOwner(newowner.get());
                }
                break;
            default:
                break;
        }
        return activity;
    }
}
