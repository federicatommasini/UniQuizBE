package com.polimi.dima.Uniquiz.uniquiz.Service;

import com.polimi.dima.Uniquiz.uniquiz.Domain.QuizEntity;
import com.polimi.dima.Uniquiz.uniquiz.Domain.UserEntity;
import com.polimi.dima.Uniquiz.uniquiz.Mappers.QuizMapper;
import com.polimi.dima.Uniquiz.uniquiz.Mappers.SubjectMapper;
import com.polimi.dima.Uniquiz.uniquiz.Mappers.UserMapper;
import com.polimi.dima.Uniquiz.uniquiz.Model.*;
import com.polimi.dima.Uniquiz.uniquiz.Repository.QuizRepository;
import com.polimi.dima.Uniquiz.uniquiz.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository repository;
    private QuizRepository quizRepo;
    private SubjectService subjectService;

    public User registration(RegistrationRequest request){
        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());
        user.setUsername(request.getUsername());
        user.setUniversityId(request.getUniversityName());
        user.setExams(Collections.emptyList());
        //user.setSchedules(Collections.emptyList());
        user.setSubjectIds(Collections.emptyList());
        user.setProfilePicUrl(new String(""));
        user.setQuestionsAdded(0);
        user.setQuestionsReported(0);

        var entity = UserMapper.INSTANCE.toEntity(user);
        var savedEntity = repository.save(entity);
        return UserMapper.INSTANCE.fromEntity(savedEntity);
    }
    public Response login(LoginRequest loginRequest){
        var entity = repository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
        User user = null;
        if(entity.isPresent()){
            user = UserMapper.INSTANCE.fromEntity(entity.get());
            return new Response(ResponseValidity.VALID, user);
        }
        else return new Response(ResponseValidity.NOTVALID, user);
    }

    public User getUserById(String id){
        var entity = repository.findById(id);
        return entity.map(UserMapper.INSTANCE::fromEntity).orElse(null);
    }

    public List<User> getUsers(){
        List<UserEntity> users = repository.findAll();
        return users.stream().map(UserMapper.INSTANCE::fromEntity).collect(Collectors.toList());
    }
    public List<Subject> getSubjects(String userId){
        User user = getUserById(userId);
        if (user != null) {
            List<Subject> subjects = new ArrayList<>();
            for(String subjectId : user.getSubjectIds()){
                subjects.add(subjectService.getSubjectById(subjectId));
            }
            return subjects;
        }
        return null;
    }

    public User addSubject(Subject subject, String userId){
        User user = getUserById(userId);
        List<String> userSubject = user.getSubjectIds();
        if(!userSubject.contains(subject.getId())) {
            userSubject.add(subject.getId());
            user.setSubjectIds(userSubject);
            repository.save(UserMapper.INSTANCE.toEntity(user));
            Subject sub = subjectService.getSubjectById(subject.getId());
            Map<String,Integer> mapRanking = sub.getRanking();
            mapRanking.put(userId,0);
            sub.setRanking(mapRanking);
            subjectService.save(SubjectMapper.INSTANCE.toEntity(sub));
        }
        return user;
    }

    public User updateProfile(User user) {
        var savedEntity = repository.save(UserMapper.INSTANCE.toEntity(user));
        return UserMapper.INSTANCE.fromEntity(savedEntity);
    }

    public User uploadPic(User user) {
        var savedEntity = repository.save(UserMapper.INSTANCE.toEntity(user));
        return UserMapper.INSTANCE.fromEntity(savedEntity);
    }

    public User addExam(UserExam savedExam, String userId) {
        User user = getUserById(userId);
        if(user.getExams().isEmpty()){
            List<UserExam> exams = new ArrayList<UserExam>();
            exams.add(savedExam);
            user.setExams(exams);
        }
        else{
            List<UserExam> examsAlreadyPresent = user.getExams();
            examsAlreadyPresent.add(savedExam);
            user.setExams(examsAlreadyPresent);
        }
        return updateProfile(user);
    }

    public int completedSubjectsUser(String userId) {
        User user = getUserById(userId);
        int howManySubjects = 0;
        for(String subjectId : user.getSubjectIds()){
            Boolean allDone = true;
            Subject subject = subjectService.getSubjectById(subjectId);
            for(String quizId : subject.getQuizIds()){
                Optional<QuizEntity> quizEntity = quizRepo.findById(quizId);
                Quiz quiz = QuizMapper.INSTANCE.fromEntity(quizEntity.orElse(null));
                if(quiz.getScore() == null || !quiz.getScore().containsKey(userId)){
                    allDone = false;
                }
            }
            if(allDone){
                howManySubjects++;
            }
        }
        return howManySubjects;
    }

    public int getPoints(String userId) {
        User user = getUserById(userId);
        int totPoints = 0;
        for(String subjectId : user.getSubjectIds()){
            Subject subject = subjectService.getSubjectById(subjectId);
            for(String quizId : subject.getQuizIds()){
                Optional<QuizEntity> quizEntity = quizRepo.findById(quizId);
                Quiz quiz = QuizMapper.INSTANCE.fromEntity(quizEntity.orElse(null));
                if(quiz.getScore() != null && quiz.getScore().containsKey(userId)){
                    totPoints += quiz.getScore().get(userId);
                }
            }
        }
        return totPoints;
    }
}
