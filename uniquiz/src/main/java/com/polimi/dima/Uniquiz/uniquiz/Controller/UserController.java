package com.polimi.dima.Uniquiz.uniquiz.Controller;

import com.polimi.dima.Uniquiz.uniquiz.Model.User;
import com.polimi.dima.Uniquiz.uniquiz.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService service;
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
        return service.registration(user);
    }
    @PostMapping("/login")
    public User login(@RequestBody User user){
        return service.login(user);
    }

}
