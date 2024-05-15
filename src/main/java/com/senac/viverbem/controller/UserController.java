package com.senac.viverbem.controller;

import com.senac.viverbem.domain.address.AddressModel;
import com.senac.viverbem.domain.address.AddressRepository;
import com.senac.viverbem.domain.address.AddressRequestDTO;
import com.senac.viverbem.domain.user.UserModel;
import com.senac.viverbem.domain.user.UserRepository;
import com.senac.viverbem.domain.user.UserRequestDTO;
import com.senac.viverbem.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRequestDTO data){
        AddressRequestDTO address = new AddressRequestDTO(data);
        AddressModel createdAddress = addressService.createAddress(address);
        UserModel user = new UserModel(data, createdAddress.getId());
        System.out.print(user);
        UserModel response = repository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
