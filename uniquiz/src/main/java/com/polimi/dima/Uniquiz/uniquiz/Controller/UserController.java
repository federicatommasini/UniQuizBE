package com.polimi.dima.Uniquiz.uniquiz.Controller;

import com.polimi.dima.Uniquiz.uniquiz.Model.LoginRequest;
import com.polimi.dima.Uniquiz.uniquiz.Model.Response;
import com.polimi.dima.Uniquiz.uniquiz.Model.University;
import com.polimi.dima.Uniquiz.uniquiz.Model.User;
import com.polimi.dima.Uniquiz.uniquiz.Service.UniversityService;
import com.polimi.dima.Uniquiz.uniquiz.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService service;
    private final UniversityService uniService;

    @GetMapping("/users")
    public List<User> getUsers(){
        return service.getUsers();
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id){
        return service.getUserById(id);
    }
    @PostMapping("/registration")
    public User registration(@RequestBody User user){
        University uni = uniService.getUniversityByName(user.getUniversityId());
        if(uni != null){
            user.setUniversityId(uni.getId());
        }
        else{
            //TO DO: case for when a student of a non-present university is registering
        }
        return service.registration(user);
    }
    @PostMapping("/login")
    public Response login(@RequestBody LoginRequest loginRequest){
        return service.login(loginRequest);
    }

}
