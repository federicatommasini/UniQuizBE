package com.polimi.dima.Uniquiz.uniquiz.Repository;

import com.polimi.dima.Uniquiz.uniquiz.Domain.QuizEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuizRepository extends MongoRepository<QuizEntity, String> {

}