package com.polimi.dima.Uniquiz.uniquiz.Service;

import com.polimi.dima.Uniquiz.uniquiz.Mappers.UniversityMapper;
import com.polimi.dima.Uniquiz.uniquiz.Mappers.UserMapper;
import com.polimi.dima.Uniquiz.uniquiz.Model.University;
import com.polimi.dima.Uniquiz.uniquiz.Model.User;
import com.polimi.dima.Uniquiz.uniquiz.Repository.UniversityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UniversityService {

    private UniversityRepository repository;

    public University getUniverisityById(String id){
        var entity = repository.findById(id);
        if(entity.isPresent())
            return UniversityMapper.INSTANCE.fromEntity(entity.get());
        else return null;
    }

    public University getUniversityByName(String name){
        var entity = repository.findByName(name);
        if(entity.isPresent())
            return UniversityMapper.INSTANCE.fromEntity(entity.get());
        else return null;
    }

    public University registerUni(University university){
        var entity = UniversityMapper.INSTANCE.toEntity(university);
        var savedEntity = repository.save(entity);
        return UniversityMapper.INSTANCE.fromEntity(savedEntity);
    }

}
