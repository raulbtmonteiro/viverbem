package com.senac.viverbem.controller;

import com.senac.viverbem.domain.activity.ActivityModel;
import com.senac.viverbem.domain.activity.ActivityRepository;
import com.senac.viverbem.domain.activity.ActivityRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityRepository repository;

    @GetMapping
    public List<ActivityModel> getAll(){
        List<ActivityModel> activities = repository.findAll();
        return activities;
    }

    @PostMapping
    public void createActivity(@RequestBody ActivityRequestDTO data){
        ActivityModel activity = new ActivityModel(data);
        repository.save(activity);
        return;
    }

}
