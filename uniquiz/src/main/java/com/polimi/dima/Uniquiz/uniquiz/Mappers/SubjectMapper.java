package com.polimi.dima.Uniquiz.uniquiz.Mappers;

import com.polimi.dima.Uniquiz.uniquiz.Domain.SubjectEntity;
import com.polimi.dima.Uniquiz.uniquiz.Domain.UserEntity;
import com.polimi.dima.Uniquiz.uniquiz.Model.Subject;
import com.polimi.dima.Uniquiz.uniquiz.Model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface SubjectMapper {
    SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);

    Subject fromEntity(SubjectEntity entity);

    SubjectEntity toEntity(Subject model);

    SubjectEntity updateEntity(@MappingTarget SubjectEntity entity, Subject user);
}
