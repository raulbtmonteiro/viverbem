package com.senac.viverbem.controller;

import com.senac.viverbem.domain.activity.ActivityModel;
import com.senac.viverbem.domain.activity.ActivityPatchRequest;
import com.senac.viverbem.domain.activity.ActivityRequestDTO;
import com.senac.viverbem.domain.address.AddressModel;
import com.senac.viverbem.domain.address.AddressRequestDTO;
import com.senac.viverbem.domain.user.UserModel;
import com.senac.viverbem.service.ActivityService;
import com.senac.viverbem.service.AddressService;
import com.senac.viverbem.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    private final AddressService addressService;
    private final ActivityService activityService;
    private final UserService userService;

    public ActivityController(AddressService addressService, ActivityService activityService, UserService userService) {
        this.addressService = addressService;
        this.activityService = activityService;
        this.userService = userService;
    }

    @GetMapping
    public List<ActivityModel> getAll(){
        List<ActivityModel> activities = activityService.getAllActivities();
        return activities;
    }

    @GetMapping("/{id}")
    public ResponseEntity getActivityById(@PathVariable Long id){
        try {
            Optional<ActivityModel> response = activityService.getActivityById(id);
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
        try{
            AddressRequestDTO address = new AddressRequestDTO(data);
            AddressModel createdAddress = addressService.createAddress(address);
            Optional<UserModel> owner = userService.getUserById(data.owner());
            if(owner.isPresent()){
                ActivityModel activity = new ActivityModel(data, createdAddress, owner.get());
                ActivityModel response = activityService.saveActivity(activity);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
            }
        } catch (Exception err){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar atividade");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteActivity(@PathVariable Long id){
        try {
            activityService.deleteActivity(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception err){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar objeto");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateActivity(@PathVariable Long id, @RequestBody ActivityPatchRequest patchRequest){
        try {
            Optional<ActivityModel> activity = activityService.getActivityById(id);
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
                ActivityModel response = activityService.saveActivity(newActivity);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception err){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar objeto");
        }
    }

}
