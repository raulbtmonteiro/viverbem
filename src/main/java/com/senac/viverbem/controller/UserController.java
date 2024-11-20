package com.senac.viverbem.controller;

import com.senac.viverbem.domain.user.dto.SubscribeActivityDTO;
import com.senac.viverbem.domain.user.dto.UserDTO;
import com.senac.viverbem.domain.user.dto.UserPostDTO;
import com.senac.viverbem.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAll(){
        List<UserDTO> users = userService.getAllUsers();
        return users;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        try {
            Optional<UserDTO> response = userService.getUserById(id);
            if (response.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception err){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao procurar objeto");
        }
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody UserPostDTO user){
        try {
            UserDTO response = userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception err){
            return ResponseEntity.badRequest().body(err.getMessage());
        }
    }

    @PatchMapping("/{id}/activities")
    public ResponseEntity subscribeActivity(@PathVariable Long id, @RequestBody SubscribeActivityDTO data){
        if(data.action().toString().equals("subscribe")) {
            Optional<UserDTO> response = userService.addActivity(id, data.activityId());
            if (response.isPresent()){
                return ResponseEntity.ok(response.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
            }
        } else if (data.action().toString().equals("unsubscribe")){
            Optional<UserDTO> response = userService.removeActivity(id, data.activityId());
            if (response.isPresent()){
                return ResponseEntity.ok(response.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ação não permitida");
    }

//    @Transactional
//    @PutMapping("/{id}")
//    public ResponseEntity updateUser(@RequestBody UserDTO data){
//        try{
//            Optional<UserDTO> user = userService.getUserById(data.getId());
//            if(user.isPresent()){
//                user.get().setFirstname(data.getFirstname());
//                user.get().setLastname(data.getLastname());
//                user.get().setEmail(data.getEmail());
//                user.get().setDateofbirth(data.getDateofbirth());
//                user.get().setGender(data.getGender());
//                user.get().setCpf(data.getCpf());
//                user.get().setPhone(data.getPhone());
//                user.get().setMedications(data.getMedications());
//                user.get().setEmergencycontact(data.getEmergencycontact());
//                user.get().setAddress(data.getAddress());
////                Optional<AddressModel> address = addressService.findAddressById(user.get().getAddress().getId());
////                if(address.isPresent()){
////                    address.get().setStreet(data.city());
////                    address.get().setCity(data.city());
////                    address.get().setPostal_code(data.postal_code());
////                    address.get().setNeighborhood(data.neighborhood());
////                    address.get().setState(data.state());
////                    address.get().setCountry(data.country());
////                    addressService.updateAddress(address.get());
////                }
//                UserDTO updatedUser = userService.saveUser(user.get());
//                return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
//            }
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não encontrado");
//        } catch (Exception err){
//            System.out.println(err);
//            return ResponseEntity.badRequest().body(err.getMessage());
//        }
//    }
}
