package com.senac.viverbem.controller;

import com.senac.viverbem.domain.address.AddressDTO;
import com.senac.viverbem.domain.address.AddressPatchRequest;
import com.senac.viverbem.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public List<AddressDTO> getAll(){
        return addressService.getAllAddresses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long id){
        try {
            Optional<AddressDTO> response = addressService.findAddressById(id);
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
    public ResponseEntity<AddressDTO> createAddress(@RequestBody AddressDTO data){
        try{
            AddressDTO response = addressService.createAddress(data);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception error) {
            System.out.println(error);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAddress(@PathVariable Long id){
        try {
            addressService.deleteAddress(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception err){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar objeto");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateAddress(@PathVariable Long id, @RequestBody AddressPatchRequest patchRequest){
        try {
            Optional<AddressDTO> response = addressService.findAddressById(id);
            if (response.isPresent()) {
                AddressDTO address = response.get();
                AddressDTO newAddress = addressService.partialUpdate(patchRequest.path, patchRequest.value, address);
                return ResponseEntity.status(HttpStatus.OK).body(newAddress);
            } else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception err){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar objeto");
        }
    }
}
