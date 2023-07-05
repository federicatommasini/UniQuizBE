package com.polimi.dima.Uniquiz.uniquiz.Service;

import com.polimi.dima.Uniquiz.uniquiz.Domain.QuizEntity;
import com.polimi.dima.Uniquiz.uniquiz.Domain.SubjectEntity;
import com.polimi.dima.Uniquiz.uniquiz.Mappers.QuizMapper;
import com.polimi.dima.Uniquiz.uniquiz.Mappers.SubjectMapper;
import com.polimi.dima.Uniquiz.uniquiz.Model.Quiz;
import com.polimi.dima.Uniquiz.uniquiz.Model.Subject;
import com.polimi.dima.Uniquiz.uniquiz.Repository.QuizRepository;
import com.polimi.dima.Uniquiz.uniquiz.Repository.SubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuizService {
    private QuizRepository repository;
    private SubjectRepository subjectRepo;

    public List<Quiz> getQuizzesBySubject(String subjectId){
        List<QuizEntity> entities = repository.findAll();
        SubjectEntity subject = subjectRepo.findById(subjectId).get();
        List<Quiz> quizzes = entities.stream().filter(q -> subject.getQuizIds().contains(q.getId())).map(q-> QuizMapper.INSTANCE.fromEntity(q)).collect(Collectors.toList());
        return quizzes;
    }

    public Quiz getQuizById(String id){
        Optional<QuizEntity> quiz = repository.findById(id);
        return quiz.map(QuizMapper.INSTANCE::fromEntity).orElse(null);
    }

    public Quiz addScore(String quizId, String userId, Integer score){
        Quiz quiz = getQuizById(quizId);
        Map<String, Integer> scoreMap ;
        if(null!=quiz.getScore())
            scoreMap = quiz.getScore();
        else scoreMap = new HashMap<>();
        scoreMap.put(userId,score);
        quiz.setScore(scoreMap);
        repository.save(QuizMapper.INSTANCE.toEntity(quiz));
        return quiz;
    }

}
