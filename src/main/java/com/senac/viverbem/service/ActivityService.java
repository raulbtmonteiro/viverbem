package com.senac.viverbem.service;

import com.senac.viverbem.domain.activity.ActivityModel;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

    public static ActivityModel updateActivity(String path, String value, ActivityModel activity){
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
            case "local":
                //activity.setLocal(Long.parseLong(value));
               break;
            case "owner":
                //activity.setOwner(Long.parseLong(value));
                break;
            default:
                break;
        }
        return activity;
    }
}
