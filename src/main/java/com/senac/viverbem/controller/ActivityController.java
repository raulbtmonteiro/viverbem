package com.senac.viverbem.controller;

import com.senac.viverbem.domain.activity.ActivityModel;
import com.senac.viverbem.domain.activity.ActivityPatchRequest;
import com.senac.viverbem.domain.activity.ActivityRepository;
import com.senac.viverbem.domain.activity.ActivityRequestDTO;
import com.senac.viverbem.domain.address.AddressModel;
import com.senac.viverbem.domain.address.AddressRequestDTO;
import com.senac.viverbem.service.ActivityService;
import com.senac.viverbem.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityRepository repository;
    private final AddressService addressService;
    private final ActivityService activityService;

    public ActivityController(AddressService addressService, ActivityService activityService) {
        this.addressService = addressService;
        this.activityService = activityService;
    }

    @GetMapping
    public List<ActivityModel> getAll(){
        List<ActivityModel> activities = repository.findAll();
        return activities;
    }

    @GetMapping("/{id}")
    public ResponseEntity getActivityById(@PathVariable Long id){
        try {
            Optional<ActivityModel> response = repository.findById(id);
            if (response != null) {
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception err){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao procurar objeto");
        }
    }

    @PostMapping
    public ResponseEntity createActivity(@RequestBody ActivityRequestDTO data){
        AddressRequestDTO address = new AddressRequestDTO(data);
        AddressModel createdAddress = addressService.createAddress(address);
        ActivityModel activity = new ActivityModel(data, createdAddress);
        ActivityModel response = repository.save(activity);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteActivity(@PathVariable Long id){
        try {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception err){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar objeto");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateActivity(@PathVariable Long id, @RequestBody ActivityPatchRequest patchRequest){
        try {
            Optional<ActivityModel> activity = repository.findById(id);
            if (activity.isPresent()) {
                ActivityModel activityModel = activity.get();
                String path = patchRequest.path.toString();
                if(path.equals("street") || path.equals("postal_code") || path.equals("neighborhood") || path.equals("city") || path.equals("state") || path.equals("country")){
                    Optional<AddressModel> address = addressService.findAddressById(activity.get().getId());
                    if(address.isPresent()) {
                        AddressModel newAddress = addressService.updateAddressModel(patchRequest.path, patchRequest.value,address.get());
                        AddressModel response = addressService.updateAddress(newAddress);
                        return ResponseEntity.status(HttpStatus.OK).body(response);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                }
                ActivityModel newActivity = activityService.updateActivity(patchRequest.path, patchRequest.value, activityModel);
                ActivityModel response = repository.save(newActivity);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception err){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar objeto");
        }
    }

}
