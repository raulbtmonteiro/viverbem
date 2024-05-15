package com.senac.viverbem.service;

import com.senac.viverbem.domain.address.AddressModel;
import com.senac.viverbem.domain.address.AddressRepository;
import com.senac.viverbem.domain.address.AddressRequestDTO;
import org.springframework.stereotype.Service;

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
}
