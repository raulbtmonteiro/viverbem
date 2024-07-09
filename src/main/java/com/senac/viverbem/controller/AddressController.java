package com.senac.viverbem.controller;

import com.senac.viverbem.domain.address.AddressModel;
import com.senac.viverbem.domain.address.AddressPatchRequest;
import com.senac.viverbem.domain.address.AddressRepository;
import com.senac.viverbem.domain.address.AddressRequestDTO;
import com.senac.viverbem.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<AddressModel> getAll(){
        List<AddressModel> address = addressService.getAllAddresses();
        return address;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAddressById(@PathVariable Long id){
        try {
            Optional<AddressModel> response = addressService.findAddressById(id);
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
    public ResponseEntity<AddressModel> createAddress(@RequestBody AddressRequestDTO data){
        try {
            AddressModel response = addressService.createAddress(data);
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
            Optional<AddressModel> address = addressService.findAddressById(id);
            if (address.isPresent()) {
                AddressModel addressModel = address.get();
                AddressModel newAddress = addressService.updateAddressModel(patchRequest.path, patchRequest.value, addressModel);
                AddressModel response = addressService.updateAddress(newAddress);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception err){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar objeto");
        }
    }
}
