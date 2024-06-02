package com.senac.viverbem.controller;

import com.senac.viverbem.domain.address.AddressModel;
import com.senac.viverbem.domain.address.AddressRequestDTO;
import com.senac.viverbem.domain.user.PutRequestDTO;
import com.senac.viverbem.domain.user.UserModel;
import com.senac.viverbem.domain.user.UserRepository;
import com.senac.viverbem.domain.user.UserRequestDTO;
import com.senac.viverbem.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;
    private final AddressService addressService;

    public UserController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public List<UserModel> getAll(){
        List<UserModel> users = repository.findAll();
        return users;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        try {
            Optional<UserModel> response = repository.findById(id);
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
    public ResponseEntity createUser(@RequestBody UserRequestDTO data){
        try {
            AddressRequestDTO address = new AddressRequestDTO(data);
            AddressModel createdAddress = addressService.createAddress(address);
            UserModel user = new UserModel(data, createdAddress);
            UserModel response = repository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception err){
            return ResponseEntity.badRequest().body(err.getMessage());
        }
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity updateUser(@RequestBody PutRequestDTO data){
        try{
            Optional<UserModel> user = repository.findById(Long.parseLong(data.id()));
            if(user.isPresent()){
                user.get().setFirstname(data.firstname());
                user.get().setLastname(data.lastname());
                user.get().setEmail(data.email());
                user.get().setDateofbirth(data.dateofbirth());
                user.get().setGender(data.gender());
                user.get().setCpf(data.cpf());
                user.get().setPhone(data.phone());
                user.get().setMedications(data.medications());
                user.get().setEmergencycontact(data.emergencycontact());
                Optional<AddressModel> address = addressService.findAddressById(user.get().getAddress().getId());
                if(address.isPresent()){
                    address.get().setStreet(data.city());
                    address.get().setCity(data.city());
                    address.get().setPostal_code(data.postal_code());
                    address.get().setNeighborhood(data.neighborhood());
                    address.get().setState(data.state());
                    address.get().setCountry(data.country());
                    addressService.updateAddress(address.get());
                }
                UserModel updatedUser = repository.save(user.get());
                return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não encontrado");
        } catch (Exception err){
            System.out.println(err);
            return ResponseEntity.badRequest().body(err.getMessage());
        }
    }
}
