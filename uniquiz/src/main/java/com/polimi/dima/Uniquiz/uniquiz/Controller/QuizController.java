package com.polimi.dima.Uniquiz.uniquiz.Controller;

import com.polimi.dima.Uniquiz.uniquiz.Model.NewQuestionRequest;
import com.polimi.dima.Uniquiz.uniquiz.Model.Question;
import com.polimi.dima.Uniquiz.uniquiz.Model.Quiz;
import com.polimi.dima.Uniquiz.uniquiz.Model.Subject;
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

    @GetMapping("/completedQuizzes/{userId}")
    public List<Quiz> getCompletedQuizzesByUser(@PathVariable String userId){
        return service.getCompletedQuizzesByUser(userId);
    }

    @PostMapping("/addReport/{quizId}/{index}")
    public Quiz addReport(@PathVariable String quizId, @PathVariable int index, @RequestBody String report){
        return service.addReport(quizId,index,report);
    }
}
