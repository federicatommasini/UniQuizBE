package com.polimi.dima.Uniquiz.uniquiz.Mappers;

import com.polimi.dima.Uniquiz.uniquiz.Domain.QuizEntity;
import com.polimi.dima.Uniquiz.uniquiz.Model.Question;
import com.polimi.dima.Uniquiz.uniquiz.Model.Quiz;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-20T21:52:57+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class QuizMapperImpl implements QuizMapper {

    @Override
    public Quiz fromEntity(QuizEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Quiz.QuizBuilder quiz = Quiz.builder();

        if ( entity.getId() != null ) {
            quiz.id( entity.getId() );
        }
        if ( entity.getName() != null ) {
            quiz.name( entity.getName() );
        }
        List<Question> list = entity.getQuestions();
        if ( list != null ) {
            quiz.questions( new ArrayList<Question>( list ) );
        }

        return quiz.build();
    }

    @Override
    public QuizEntity toEntity(Quiz model) {
        if ( model == null ) {
            return null;
        }

        QuizEntity quizEntity = new QuizEntity();

        if ( model.getId() != null ) {
            quizEntity.setId( model.getId() );
        }
        if ( model.getName() != null ) {
            quizEntity.setName( model.getName() );
        }
        List<Question> list = model.getQuestions();
        if ( list != null ) {
            quizEntity.setQuestions( new ArrayList<Question>( list ) );
        }

        return quizEntity;
    }

    @Override
    public QuizEntity updateEntity(QuizEntity entity, Quiz model) {
        if ( model == null ) {
            return entity;
        }

        if ( model.getId() != null ) {
            entity.setId( model.getId() );
        }
        if ( model.getName() != null ) {
            entity.setName( model.getName() );
        }
        if ( entity.getQuestions() != null ) {
            List<Question> list = model.getQuestions();
            if ( list != null ) {
                entity.getQuestions().clear();
                entity.getQuestions().addAll( list );
            }
        }
        else {
            List<Question> list = model.getQuestions();
            if ( list != null ) {
                entity.setQuestions( new ArrayList<Question>( list ) );
            }
        }

        return entity;
    }
}
