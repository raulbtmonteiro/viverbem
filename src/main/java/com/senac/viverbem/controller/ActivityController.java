package com.senac.viverbem.controller;

import com.senac.viverbem.domain.activity.dto.ActivityDTO;
import com.senac.viverbem.domain.activity.dto.ActivityPostDTO;
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
    public List<ActivityDTO> getAll(){
        return activityService.getAllActivities();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityDTO> getActivityById(@PathVariable Long id){
        try {
            Optional<ActivityDTO> response = activityService.getActivityById(id);
            if (response.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(response.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception err){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<ActivityDTO> createActivity(@RequestBody ActivityPostDTO data){
        try{
            Optional<ActivityDTO> activity = activityService.saveActivity(data);
            if(activity.isPresent()){
                return ResponseEntity.status(HttpStatus.CREATED).body(activity.get());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } catch (Exception err){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
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

//    @PatchMapping("/{id}")
//    public ResponseEntity updateActivity(@PathVariable Long id, @RequestBody ActivityPatchRequest patchRequest){
//        try {
//            Optional<ActivityDTO> activity = activityService.getActivityById(id);
//            if (activity.isPresent()) {
//                ActivityDTO activityDTO = activity.get();
//                String path = patchRequest.path.toString();
//                if(path.equals("street") || path.equals("postal_code") || path.equals("neighborhood") || path.equals("city") || path.equals("state") || path.equals("country")){
//                    Optional<AddressModel> address = addressService.findAddressById(activity.get().getId());
//                    if(address.isPresent()) {
//                        AddressModel newAddress = addressService.partialUptade(patchRequest.path, patchRequest.value,address.get());
//                        AddressModel response = addressService.updateAddress(newAddress);
//                        return ResponseEntity.status(HttpStatus.OK).body(response);
//                    } else {
//                        return ResponseEntity.notFound().build();
//                    }
//                }
//                ActivityModel newActivity = activityService.partialUptade(patchRequest.path, patchRequest.value, activityModel);
//                ActivityModel response = activityService.saveActivity(newActivity);
//                return ResponseEntity.status(HttpStatus.OK).body(response);
//            } else {
//                return ResponseEntity.notFound().build();
//            }
//        }catch (Exception err){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar objeto");
//        }
//    }

}
