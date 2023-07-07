package com.polimi.dima.Uniquiz.uniquiz.Service;

import com.polimi.dima.Uniquiz.uniquiz.Domain.QuizEntity;
import com.polimi.dima.Uniquiz.uniquiz.Domain.SubjectEntity;
import com.polimi.dima.Uniquiz.uniquiz.Mappers.QuizMapper;
import com.polimi.dima.Uniquiz.uniquiz.Mappers.SubjectMapper;
import com.polimi.dima.Uniquiz.uniquiz.Model.*;
import com.polimi.dima.Uniquiz.uniquiz.Repository.QuizRepository;
import com.polimi.dima.Uniquiz.uniquiz.Repository.SubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
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

    public Subject addQuestion(NewQuestionRequest request){
        List<Question> questions;
        List<String> quizIds;
        Quiz quiz;
        SubjectEntity subject = subjectRepo.findById(request.getSubjectId()).get();
        if(null!=request.getQuizId()){
            quiz = getQuizById(request.getQuizId());
            questions = (null!=quiz.getQuestions()) ? quiz.getQuestions() :  new ArrayList<>();
            questions.add(request.getQuestion());
            quiz.setQuestions(questions);
            repository.save(QuizMapper.INSTANCE.toEntity(quiz));
        }
        else{
            questions = new ArrayList<>();
            questions.add(request.getQuestion());
            quiz = new Quiz();
            quiz.setName(request.getQuizName());
            quiz.setQuestions(questions);
            quiz.setScore(new HashMap<String,Integer>());
            if(null!=subject.getQuizIds())
                quizIds = subject.getQuizIds();
            else quizIds = new ArrayList<>();
            QuizEntity entity = repository.save(QuizMapper.INSTANCE.toEntity(quiz));
            quizIds.add(entity.getId());
            subject.setQuizIds(quizIds);
            subjectRepo.save(subject);
        }
        return SubjectMapper.INSTANCE.fromEntity(subject);
    }

}
