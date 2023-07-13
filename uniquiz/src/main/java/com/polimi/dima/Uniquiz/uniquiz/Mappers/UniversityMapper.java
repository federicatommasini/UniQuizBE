package com.polimi.dima.Uniquiz.uniquiz.Mappers;

import com.polimi.dima.Uniquiz.uniquiz.Domain.UniversityEntity;
import com.polimi.dima.Uniquiz.uniquiz.Model.University;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UniversityMapper {

    UniversityMapper INSTANCE = Mappers.getMapper(UniversityMapper.class);

    University fromEntity(UniversityEntity entity);

    UniversityEntity toEntity(University model);

}
