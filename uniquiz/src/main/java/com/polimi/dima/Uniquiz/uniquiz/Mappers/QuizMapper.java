package com.polimi.dima.Uniquiz.uniquiz.Mappers;

import com.polimi.dima.Uniquiz.uniquiz.Domain.QuizEntity;
import com.polimi.dima.Uniquiz.uniquiz.Model.Quiz;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface QuizMapper {
    QuizMapper INSTANCE = Mappers.getMapper(QuizMapper.class);
    @Mapping(source = "score", target = "score")
    Quiz fromEntity(QuizEntity entity);
    @Mapping(source = "score", target = "score")
    QuizEntity toEntity(Quiz model);

    QuizEntity updateEntity(@MappingTarget QuizEntity entity, Quiz model);
}
