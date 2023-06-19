package com.polimi.dima.Uniquiz.uniquiz.Service;

import com.polimi.dima.Uniquiz.uniquiz.Domain.SubjectEntity;
import com.polimi.dima.Uniquiz.uniquiz.Domain.UserEntity;
import com.polimi.dima.Uniquiz.uniquiz.Mappers.SubjectMapper;
import com.polimi.dima.Uniquiz.uniquiz.Mappers.UserMapper;
import com.polimi.dima.Uniquiz.uniquiz.Model.Subject;
import com.polimi.dima.Uniquiz.uniquiz.Model.User;
import com.polimi.dima.Uniquiz.uniquiz.Repository.SubjectRepository;
import com.polimi.dima.Uniquiz.uniquiz.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubjectService {

    private SubjectRepository repository;

    public List<Subject> getSubjects(){
        List<SubjectEntity> subjects = repository.findAll();
        return subjects.stream().map(s-> SubjectMapper.INSTANCE.fromEntity(s)).collect(Collectors.toList());
    }

    public Subject getSubjectById(String id){
        Optional<SubjectEntity> subject = repository.findById(id);
        return subject.map(SubjectMapper.INSTANCE::fromEntity).orElse(null);
    }

    public Subject getSubjectByName(String name){
        Optional<SubjectEntity> subject = repository.findByName(name);
        return subject.map(SubjectMapper.INSTANCE::fromEntity).orElse(null);
    }

}
