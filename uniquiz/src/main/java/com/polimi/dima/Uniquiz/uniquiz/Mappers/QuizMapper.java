package com.polimi.dima.Uniquiz.uniquiz.Mappers;

import com.polimi.dima.Uniquiz.uniquiz.Domain.QuizEntity;
import com.polimi.dima.Uniquiz.uniquiz.Domain.SubjectEntity;
import com.polimi.dima.Uniquiz.uniquiz.Model.Quiz;
import com.polimi.dima.Uniquiz.uniquiz.Model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface QuizMapper {
    QuizMapper INSTANCE = Mappers.getMapper(QuizMapper.class);

    Quiz fromEntity(QuizEntity entity);

    QuizEntity toEntity(Quiz model);

    QuizEntity updateEntity(@MappingTarget QuizEntity entity, Quiz model);
}
