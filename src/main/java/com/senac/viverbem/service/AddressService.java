package com.senac.viverbem.service;

import com.senac.viverbem.domain.address.dto.AddressDTO;
import com.senac.viverbem.domain.address.AddressModel;
import com.senac.viverbem.domain.address.AddressRepository;
import com.senac.viverbem.mappers.impl.AddressMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository repository;
    private final AddressMapper addressMapper;

    public AddressService(AddressRepository repository, AddressMapper addressMapper) {
        this.repository = repository;
        this.addressMapper = addressMapper;
    }

    public List<AddressDTO> getAllAddresses() {
        List<AddressModel> allAddresses = repository.findAll();
        return allAddresses.stream().map(addressMapper::mapTo).toList();
    }

    public AddressDTO createAddress(AddressDTO data) {
        AddressModel address = addressMapper.mapFrom(data);
        AddressModel savedAddress = repository.save(address);
        return addressMapper.mapTo(savedAddress);
    }

    public Optional<AddressDTO> findAddressById(Long id) {
        Optional<AddressModel> response = repository.findById(id);
        if(response.isPresent()){
            AddressDTO address = addressMapper.mapTo(response.get());
            return Optional.ofNullable(address);
        } else {
            return Optional.empty();
        }
    }

    public AddressDTO updateAddress(AddressDTO address){
        AddressModel addressToUpdate = addressMapper.mapFrom(address);
        AddressModel savedAddress =  repository.save(addressToUpdate);
        return addressMapper.mapTo(savedAddress);
    }

    public void deleteAddress(Long id) { repository.deleteById(id); }

    public AddressDTO partialUpdate(String path, String value, AddressDTO address){
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
        AddressModel addressToUpdate = addressMapper.mapFrom(address);
        AddressModel savedAddress = repository.save(addressToUpdate);
        return addressMapper.mapTo(savedAddress);
    }
}
