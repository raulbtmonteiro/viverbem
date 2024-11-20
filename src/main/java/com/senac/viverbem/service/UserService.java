package com.senac.viverbem.service;

import com.senac.viverbem.domain.user.dto.UserDTO;
import com.senac.viverbem.domain.user.UserModel;
import com.senac.viverbem.domain.user.UserRepository;
import com.senac.viverbem.domain.user.dto.UserPostDTO;
import com.senac.viverbem.mappers.impl.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Optional<String> getPasswordById(Long id){
        Optional<UserModel> user = repository.findById(id);
        if(user.isPresent()){
            return Optional.ofNullable(user.get().getPassword());
        } else {
            return Optional.empty();
        }
    }

    public List<UserDTO> getAllUsers() {
        List<UserModel> response = repository.findAll();
        return response.stream().map(userMapper::mapTo).toList();
    }

    public UserDTO saveUser(UserPostDTO user) {
        UserDTO newUser = new UserDTO(user);
        UserModel userModel = userMapper.mapFrom(newUser);
        userModel.setPassword(user.getPassword());
        UserModel savedUser = repository.save(userModel);
        return userMapper.mapTo(savedUser);
    }

    public Optional<UserDTO> updateUser(UserDTO user) {
        Optional<String> password = getPasswordById(user.getId());
        if(password.isPresent()){
            UserModel userModel = userMapper.mapFrom(user);
            userModel.setPassword(password.get());
            UserModel savedUser = repository.save(userModel);
            return Optional.ofNullable(userMapper.mapTo(savedUser));
        } else {
            return Optional.empty();
        }
    }

    public Optional<UserDTO> addActivity(Long userId, Long activityId) {
        Optional<UserModel> user = repository.findById(userId);
        if(user.isPresent()){
            List<Long> list = user.get().getActivities();
            if(list == null){
                List<Long> newList = new ArrayList<Long>();
                newList.add(activityId);
                user.get().setActivities(newList);
                UserModel savedUser = repository.save(user.get());
                return Optional.ofNullable(userMapper.mapTo(savedUser));
            }
            list.add(activityId);
            user.get().setActivities(list);
            UserModel savedUser = repository.save(user.get());
            return Optional.ofNullable(userMapper.mapTo(savedUser));
        } else {
            return Optional.empty();
        }
    }

    public Optional<UserDTO> removeActivity(Long userId, Long activityId) {
        Optional<UserModel> user = repository.findById(userId);
        if(user.isPresent()){
            List<Long> list = user.get().getActivities();
            if(list == null){
                return Optional.ofNullable(userMapper.mapTo(user.get()));
            }
            list.remove(activityId);
            user.get().setActivities(list);
            UserModel savedUser = repository.save(user.get());
            return Optional.ofNullable(userMapper.mapTo(savedUser));
        } else {
            return Optional.empty();
        }
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    public UserDetails getUserByEmail(String email) {
        return repository.findByEmail(email);
    }
}
