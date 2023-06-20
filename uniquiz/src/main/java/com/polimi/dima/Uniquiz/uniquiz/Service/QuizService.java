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

import java.util.List;
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
}
