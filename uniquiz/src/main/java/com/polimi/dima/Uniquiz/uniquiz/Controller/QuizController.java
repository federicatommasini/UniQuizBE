package com.polimi.dima.Uniquiz.uniquiz.Controller;

import com.polimi.dima.Uniquiz.uniquiz.Model.*;
import com.polimi.dima.Uniquiz.uniquiz.Service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class QuizController {


    private final QuizService service;

    @GetMapping("/quizzes/{subjectId}")
    public List<Quiz> getQuizzesBySubject(@PathVariable String subjectId){
        return service.getQuizzesBySubject(subjectId);
    }

    @GetMapping("/quizById/{quizId}")
    public Quiz getQuizById(@PathVariable String quizId){
        return service.getQuizById(quizId);
    }

    @PutMapping("/addScore/{quizId}/{userId}/{score}")
    public void addScore(@PathVariable String quizId, @PathVariable String userId, @PathVariable Integer score){
         service.addScore( quizId, userId, score);
    }

    @PostMapping("/addQuestion")
    public Subject addQuestion(@RequestBody NewQuestionRequest request){
        return service.addQuestion(request);
    }

    @PostMapping("/addReport/{quizId}/{index}/{userId}")
    public User addReport(@PathVariable String quizId, @PathVariable int index , @PathVariable String userId, @RequestBody String report) {
        return service.addReport(quizId, index, userId, report);
    }
    @GetMapping("/completedQuizzes/{userId}")
    public List<Quiz> getCompletedQuizzesByUser(@PathVariable String userId){
        return service.getCompletedQuizzesByUser(userId);
    }
}
