package com.polimi.dima.Uniquiz.uniquiz.Repository;

import com.polimi.dima.Uniquiz.uniquiz.Domain.UniversityEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UniversityRepository extends MongoRepository<UniversityEntity, String> {

    Optional<UniversityEntity> findByName(String name);
}
