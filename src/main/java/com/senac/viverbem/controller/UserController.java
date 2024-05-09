package com.senac.viverbem.controller;

import com.senac.viverbem.domain.user.UserModel;
import com.senac.viverbem.domain.user.UserRepository;
import com.senac.viverbem.domain.user.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    public List<UserModel> getAll(){
        List<UserModel> users = repository.findAll();
        return users;
    }

    @PostMapping
    public void createUser(@RequestBody UserRequestDTO data){
        UserModel user = new UserModel(data);
        repository.save(user);
        return;
    }

}
