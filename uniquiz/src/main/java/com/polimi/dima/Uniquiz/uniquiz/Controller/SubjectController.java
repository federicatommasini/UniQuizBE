package com.polimi.dima.Uniquiz.uniquiz.Controller;

import com.polimi.dima.Uniquiz.uniquiz.Model.Subject;
import com.polimi.dima.Uniquiz.uniquiz.Model.User;
import com.polimi.dima.Uniquiz.uniquiz.Service.SubjectService;
import com.polimi.dima.Uniquiz.uniquiz.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class SubjectController {

    private final SubjectService service;

    @GetMapping("/subjects")
    public List<Subject> getSubjects(){
        return service.getSubjects();
    }


    @GetMapping("/subjects/{id}")
    public Subject getSubjectById(@PathVariable String id){
        return service.getSubjectById(id);
    }

    @GetMapping("/subjectByName/{subjectName}")
    public Subject getSubjectByName(@PathVariable String subjectName){
        return service.getSubjectByName(subjectName);
    }


}
