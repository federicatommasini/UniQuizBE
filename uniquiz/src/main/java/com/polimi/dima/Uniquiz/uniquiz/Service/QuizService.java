package com.polimi.dima.Uniquiz.uniquiz.Service;

import com.polimi.dima.Uniquiz.uniquiz.Domain.QuizEntity;
import com.polimi.dima.Uniquiz.uniquiz.Domain.SubjectEntity;
import com.polimi.dima.Uniquiz.uniquiz.Domain.UserEntity;
import com.polimi.dima.Uniquiz.uniquiz.Mappers.QuizMapper;
import com.polimi.dima.Uniquiz.uniquiz.Mappers.SubjectMapper;
import com.polimi.dima.Uniquiz.uniquiz.Mappers.UserMapper;
import com.polimi.dima.Uniquiz.uniquiz.Model.*;
import com.polimi.dima.Uniquiz.uniquiz.Repository.QuizRepository;
import com.polimi.dima.Uniquiz.uniquiz.Repository.SubjectRepository;
import com.polimi.dima.Uniquiz.uniquiz.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuizService {

    private QuizRepository repository;
    private SubjectRepository subjectRepo;
    private UserRepository userRepo;


    public List<Quiz> getQuizzesBySubject(String subjectId){
        List<QuizEntity> entities = repository.findAll();
        SubjectEntity subject = subjectRepo.findById(subjectId).get();
        List<Quiz> quizzes = entities.stream().filter(q -> subject.getQuizIds().contains(q.getId())).map(q-> QuizMapper.INSTANCE.fromEntity(q)).collect(Collectors.toList());
        return quizzes;
    }

    public Quiz getQuizById(String id){
        Optional<QuizEntity> entityQuiz = repository.findById(id);
        return QuizMapper.INSTANCE.fromEntity(entityQuiz.orElse(null));
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
        UserEntity userEntity = userRepo.findById(request.getUserId()).orElse(null);
        User user = UserMapper.INSTANCE.fromEntity(userEntity);
        user.setQuestionsAdded(user.getQuestionsAdded()+1);
        userRepo.save(UserMapper.INSTANCE.toEntity(user));
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

    public List<Quiz> getCompletedQuizzesByUser(String userId) {
        List<QuizEntity> quizzes = repository.findAll();
        List<Quiz> allQuizzes = quizzes.stream().map(QuizMapper.INSTANCE::fromEntity).collect(Collectors.toList());
        List<Quiz> onlyYourQuizzes = new ArrayList<Quiz>();
        for(Quiz quiz : allQuizzes){
            if(quiz.getScore() != null && quiz.getScore().get(userId) != null){
                onlyYourQuizzes.add(quiz);
            }
        }
        return onlyYourQuizzes;
    }
    public Quiz addReport(String quizId, int index,String report){
    public User addReport(String quizId, int index, String userId, String report){
        Optional<UserEntity> user = userRepo.findById(userId);
        UserEntity newUser = null;
        if(user.get()!=null){
            user.get().setQuestionsReported(user.get().getQuestionsReported()+1);
            newUser = userRepo.save(user.get());
        }
        Quiz quiz = getQuizById(quizId);
        Question question = quiz.getQuestions().get(index);
        List<String> reports ;
        if(null!=question.getReports()){
            reports=question.getReports();
        }else reports = new ArrayList<>();
        reports.add(report);
        question.setReports(reports);
        quiz.getQuestions().set(index,question);
        repository.save(QuizMapper.INSTANCE.toEntity(quiz));
        return UserMapper.INSTANCE.fromEntity(newUser);
    }

}
