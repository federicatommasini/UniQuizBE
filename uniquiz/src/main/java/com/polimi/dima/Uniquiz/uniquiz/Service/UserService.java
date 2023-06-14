package com.polimi.dima.Uniquiz.uniquiz.Service;

import com.polimi.dima.Uniquiz.uniquiz.Domain.UserEntity;
import com.polimi.dima.Uniquiz.uniquiz.Mappers.UserMapper;
import com.polimi.dima.Uniquiz.uniquiz.Model.LoginRequest;
import com.polimi.dima.Uniquiz.uniquiz.Model.User;
import com.polimi.dima.Uniquiz.uniquiz.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository repository;

    public User registration(User user){
        var entity = UserMapper.INSTANCE.toEntity(user);
        var savedEntity=repository.save(entity);
        return UserMapper.INSTANCE.fromEntity(savedEntity);
    }
    public User login(LoginRequest loginRequest){
        var entity= repository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
        if(entity.isPresent())
            return UserMapper.INSTANCE.fromEntity(entity.get());
        else return null;
    }

    public User getUserById(String id){
        var entity= repository.findById(id);
        if(entity.isPresent())
            return UserMapper.INSTANCE.fromEntity(entity.get());
        else return null;
    }

    public List<User> getUsers(){
        List<UserEntity> users = repository.findAll();
        return users.stream().map(u-> UserMapper.INSTANCE.fromEntity(u)).collect(Collectors.toList());
    }
}
