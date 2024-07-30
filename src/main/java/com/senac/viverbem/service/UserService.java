package com.senac.viverbem.service;

import com.senac.viverbem.domain.user.UserDTO;
import com.senac.viverbem.domain.user.UserModel;
import com.senac.viverbem.domain.user.UserRepository;
import com.senac.viverbem.mappers.impl.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    @Autowired
    private UserMapper userMapper;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<UserDTO> getUserById(Long id){
        Optional<UserModel> user = repository.findById(id);
        if(user.isPresent()){
            UserDTO userDto = userMapper.mapTo(user.get());
            return Optional.ofNullable(userDto);
        } else {
            return Optional.empty();
        }
    }

    public List<UserDTO> getAllUsers() {
        List<UserModel> response = repository.findAll();
        return response.stream().map(userMapper::mapTo).toList();
    }

    public UserDTO saveUser(UserDTO user) {
        UserModel userModel = userMapper.mapFrom(user);
        UserModel savedUser = repository.save(userModel);
        return userMapper.mapTo(savedUser);
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    public UserDetails getUserByEmail(String email) {
        return repository.findByEmail(email);
    }
}
