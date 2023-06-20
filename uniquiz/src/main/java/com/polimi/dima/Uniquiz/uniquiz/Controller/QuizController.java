package com.polimi.dima.Uniquiz.uniquiz.Controller;

import com.polimi.dima.Uniquiz.uniquiz.Model.Quiz;
import com.polimi.dima.Uniquiz.uniquiz.Service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class QuizController {


    private final QuizService service;

    @GetMapping("/quizzes/{subjectId}")
    public List<Quiz> getQuizzesBySubject(@PathVariable String subjectId){
        return service.getQuizzesBySubject(subjectId);
    }
}
