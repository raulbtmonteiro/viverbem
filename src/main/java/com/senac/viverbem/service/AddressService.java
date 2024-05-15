package com.senac.viverbem.service;

import com.senac.viverbem.domain.address.AddressModel;
import com.senac.viverbem.domain.address.AddressRepository;
import com.senac.viverbem.domain.address.AddressRequestDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository repository;

    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    public AddressModel createAddress(AddressRequestDTO data) {
        AddressModel address = new AddressModel(data);
        return repository.save(address);
    }

    public static AddressModel updateAddress(String path, String value, AddressModel address){
        switch (path){
            case "street":
                address.setStreet(value);
                break;
            case "postal_code":
                address.setPostal_code(value);
                break;
            case "neighborhood":
                address.setNeighborhood(value);
                break;
            case "city":
                address.setCity(value);
                break;
            case "state":
                address.setState(value);
                break;
            case "country":
                address.setCountry(value);
                break;
            default:
                break;
        }
        return address;
    }
}
