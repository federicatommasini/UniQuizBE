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

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable String userId){
        return service.getUserById(userId);
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

    @PutMapping("/{id}/pic")
    public User uploadProfilePic(@PathVariable String id, @RequestBody User user){
        return service.uploadPic(user);
    }

    @GetMapping("/{id}/subjects")
    public List<Subject> getSubjectsByUser(@PathVariable String id){
        return service.getSubjects(id);
    }

    @PostMapping("/{id}/addSubject")
    public User addSubject(@RequestBody Subject subject, @PathVariable String id){
        return service.addSubject(subject, id);
    }

    @PutMapping("/updateProfile/{id}")
    public User updateProfile(@RequestBody User user, @PathVariable String id){
        return service.updateProfile(user);
    }

    @GetMapping("/completedSubjects/{userId}")
    public int completedSubjectsUser(@PathVariable String userId){
        return service.completedSubjectsUser(userId);
    }

    @GetMapping("/{userId}/points")
    public int getPoints(@PathVariable String userId){
        return service.getPoints(userId);
    }
}
