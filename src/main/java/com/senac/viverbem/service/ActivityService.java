package com.senac.viverbem.service;

import com.senac.viverbem.domain.activity.ActivityModel;
import com.senac.viverbem.domain.activity.ActivityRepository;
import com.senac.viverbem.domain.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository repository;
    private final UserService userService;

    public ActivityService(UserService userService) {
        this.userService = userService;
    }

    public List<ActivityModel> getAllActivities() { return repository.findAll(); }

    public Optional<ActivityModel> getActivityById(Long id) { return repository.findById(id); }

    public ActivityModel saveActivity(ActivityModel activity) { return repository.save(activity); }

    public void deleteActivity(Long id) { repository.deleteById(id); }

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
                Optional<UserModel> newowner = userService.getUserById(Long.parseLong(value));
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
