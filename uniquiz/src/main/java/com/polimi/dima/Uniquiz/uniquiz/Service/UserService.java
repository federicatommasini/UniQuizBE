package com.polimi.dima.Uniquiz.uniquiz.Service;

import com.polimi.dima.Uniquiz.uniquiz.Mappers.UserMapper;
import com.polimi.dima.Uniquiz.uniquiz.Model.User;
import com.polimi.dima.Uniquiz.uniquiz.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository repository;

    public User registration(User user){
        var entity = UserMapper.INSTANCE.toEntity(user);
        var savedEntity=repository.save(entity);
        return UserMapper.INSTANCE.fromEntity(savedEntity);
    }
    public User login(User user){
        var entity= repository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if(entity.isPresent())
            return UserMapper.INSTANCE.fromEntity(entity.get());
        else return null;
    }
}
