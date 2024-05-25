package com.senac.viverbem.service;

import com.senac.viverbem.domain.user.UserModel;
import com.senac.viverbem.domain.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
            this.repository = repository;
        }

    public Optional<UserModel> getUser(Long id){
        return repository.findById(id);
    }

}
