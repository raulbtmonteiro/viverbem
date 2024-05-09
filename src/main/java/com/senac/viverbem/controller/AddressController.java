package com.senac.viverbem.controller;

import com.senac.viverbem.domain.address.AddressModel;
import com.senac.viverbem.domain.address.AddressRepository;
import com.senac.viverbem.domain.address.AddressRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressRepository repository;

    @GetMapping
    public List<AddressModel> getAll(){
        List<AddressModel> address = repository.findAll();
        return address;
    }

    @PostMapping
    public void createAddress(@RequestBody AddressRequestDTO data){
        AddressModel address = new AddressModel(data);
        repository.save(address);
        return;
    }
}
