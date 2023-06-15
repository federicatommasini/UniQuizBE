package com.polimi.dima.Uniquiz.uniquiz.Repository;

import com.polimi.dima.Uniquiz.uniquiz.Domain.SubjectEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepository extends MongoRepository<SubjectEntity, String> {

}
