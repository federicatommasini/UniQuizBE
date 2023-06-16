package com.polimi.dima.Uniquiz.uniquiz.Mappers;

import com.polimi.dima.Uniquiz.uniquiz.Domain.SubjectEntity;
import com.polimi.dima.Uniquiz.uniquiz.Model.Subject;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-15T18:52:44+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class SubjectMapperImpl implements SubjectMapper {

    @Override
    public Subject fromEntity(SubjectEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Subject.SubjectBuilder subject = Subject.builder();

        if ( entity.getId() != null ) {
            subject.id( entity.getId() );
        }
        if ( entity.getCode() != null ) {
            subject.code( entity.getCode() );
        }
        if ( entity.getName() != null ) {
            subject.name( entity.getName() );
        }
        List<String> list = entity.getQuizIds();
        if ( list != null ) {
            subject.quizIds( new ArrayList<String>( list ) );
        }
        Map<String, Integer> map = entity.getRanking();
        if ( map != null ) {
            subject.ranking( new LinkedHashMap<String, Integer>( map ) );
        }

        return subject.build();
    }

    @Override
    public SubjectEntity toEntity(Subject model) {
        if ( model == null ) {
            return null;
        }

        SubjectEntity subjectEntity = new SubjectEntity();

        if ( model.getId() != null ) {
            subjectEntity.setId( model.getId() );
        }
        if ( model.getCode() != null ) {
            subjectEntity.setCode( model.getCode() );
        }
        if ( model.getName() != null ) {
            subjectEntity.setName( model.getName() );
        }
        List<String> list = model.getQuizIds();
        if ( list != null ) {
            subjectEntity.setQuizIds( new ArrayList<String>( list ) );
        }
        Map<String, Integer> map = model.getRanking();
        if ( map != null ) {
            subjectEntity.setRanking( new LinkedHashMap<String, Integer>( map ) );
        }

        return subjectEntity;
    }

    @Override
    public SubjectEntity updateEntity(SubjectEntity entity, Subject user) {
        if ( user == null ) {
            return entity;
        }

        if ( user.getId() != null ) {
            entity.setId( user.getId() );
        }
        if ( user.getCode() != null ) {
            entity.setCode( user.getCode() );
        }
        if ( user.getName() != null ) {
            entity.setName( user.getName() );
        }
        if ( entity.getQuizIds() != null ) {
            List<String> list = user.getQuizIds();
            if ( list != null ) {
                entity.getQuizIds().clear();
                entity.getQuizIds().addAll( list );
            }
        }
        else {
            List<String> list = user.getQuizIds();
            if ( list != null ) {
                entity.setQuizIds( new ArrayList<String>( list ) );
            }
        }
        if ( entity.getRanking() != null ) {
            Map<String, Integer> map = user.getRanking();
            if ( map != null ) {
                entity.getRanking().clear();
                entity.getRanking().putAll( map );
            }
        }
        else {
            Map<String, Integer> map = user.getRanking();
            if ( map != null ) {
                entity.setRanking( new LinkedHashMap<String, Integer>( map ) );
            }
        }

        return entity;
    }
}
