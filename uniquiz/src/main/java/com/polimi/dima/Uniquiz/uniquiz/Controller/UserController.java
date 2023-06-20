package com.polimi.dima.Uniquiz.uniquiz.Controller;

import com.polimi.dima.Uniquiz.uniquiz.Model.*;
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
    public User registration(@RequestBody RegistrationRequest registrationRequest){
        University uni = uniService.getUniversityByName(registrationRequest.getUniversityName());
        if(uni != null){
            registrationRequest.setUniversityName(uni.getId());
        }
        return service.registration(registrationRequest);
    }

    @PostMapping("/login")
    public Response login(@RequestBody LoginRequest loginRequest){
        return service.login(loginRequest);
    }


    @GetMapping("/{id}/subjects")
    public List<Subject> getSubjectsByUser(@PathVariable String id){
        return service.getSubjects(id);
    }
}
