package com.senac.viverbem.service;

import com.senac.viverbem.domain.address.AddressModel;
import com.senac.viverbem.domain.address.AddressRepository;
import com.senac.viverbem.domain.address.AddressRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository repository;

    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    public List<AddressModel> getAllAddresses() { return repository.findAll(); }

    public AddressModel createAddress(AddressRequestDTO data) {
        AddressModel address = new AddressModel(data);
        return repository.save(address);
    }

    public Optional<AddressModel> findAddressById(Long id) {
        return repository.findById(id);
    }

    public AddressModel updateAddress(AddressModel address){
        return repository.save(address);
    }

    public void deleteAddress(Long id) { repository.deleteById(id); }

    public static AddressModel updateAddressModel(String path, String value, AddressModel address){
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
