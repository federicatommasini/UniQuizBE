package com.polimi.dima.Uniquiz.uniquiz.Controller;

import com.polimi.dima.Uniquiz.uniquiz.Model.University;
import com.polimi.dima.Uniquiz.uniquiz.Service.UniversityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UniversityController {

    private final UniversityService service;

    @GetMapping("/university/{id}")
    public University getUserById(@PathVariable String id){
        return service.getUniverisityById(id);
    }

    @PostMapping("/registerUni")
    public University registration(@RequestBody University university){
        return service.registerUni(university);
    }

}
