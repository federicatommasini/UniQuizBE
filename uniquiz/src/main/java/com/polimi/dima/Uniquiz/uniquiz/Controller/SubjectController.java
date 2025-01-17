package com.polimi.dima.Uniquiz.uniquiz.Controller;

import com.polimi.dima.Uniquiz.uniquiz.Model.Subject;
import com.polimi.dima.Uniquiz.uniquiz.Service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/subjectById/{subjectId}")
    public Subject getSubjectById(@PathVariable String subjectId){
        return service.getSubjectById(subjectId);
    }

    @GetMapping("/documents/{subjectId}")
    public List<String> getDocumentsLinks(@PathVariable String subjectId){
        return service.getDocumentsLinks(subjectId);
    }
    @PutMapping("/updateRanking/{subjectId}/{userId}")
    public void updateRanking(@PathVariable String subjectId, @PathVariable String userId){
        service.updateRanking(subjectId,userId);
    }
}
