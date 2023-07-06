package com.polimi.dima.Uniquiz.uniquiz.Service;

import com.polimi.dima.Uniquiz.uniquiz.Domain.UserEntity;
import com.polimi.dima.Uniquiz.uniquiz.Mappers.SubjectMapper;
import com.polimi.dima.Uniquiz.uniquiz.Mappers.UserMapper;
import com.polimi.dima.Uniquiz.uniquiz.Model.*;
import com.polimi.dima.Uniquiz.uniquiz.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository repository;
    private SubjectService subjectService;

    public User registration(RegistrationRequest request){
        val user = new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());
        user.setUsername(request.getUsername());
        user.setUniversityId(request.getUniversityName());
        user.setExams(Collections.emptyList());
        user.setSchedules(Collections.emptyList());
        user.setSubjectIds(Collections.emptyList());
        user.setProfilePicUrl(new String(""));

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

    public User addExam(Exam savedExam, String userId) {
        User user = getUserById(userId);
        if(user.getExams().isEmpty()){
            List<Exam> exams = new ArrayList<Exam>();
            exams.add(savedExam);
            user.setExams(exams);
        }
        else{
            user.getExams().add(savedExam);
        }
        return updateProfile(user);
    }
}
