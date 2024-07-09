package com.senac.viverbem.service;

import com.senac.viverbem.domain.user.UserModel;
import com.senac.viverbem.domain.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<UserModel> getUserById(Long id){
        return repository.findById(id);
    }

    public List<UserModel> getAllUsers() { return repository.findAll(); }

    public UserModel saveUser(UserModel user) { return repository.save(user); }

}
