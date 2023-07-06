package com.polimi.dima.Uniquiz.uniquiz.Mappers;

import com.polimi.dima.Uniquiz.uniquiz.Domain.SubjectEntity;
import com.polimi.dima.Uniquiz.uniquiz.Model.Subject;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface SubjectMapper {
    SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);
    @Mapping(source = "pdf_links", target = "pdf_links")
    Subject fromEntity(SubjectEntity entity);

    @Mapping(source = "pdf_links", target = "pdf_links")
    SubjectEntity toEntity(Subject model);

    SubjectEntity updateEntity(@MappingTarget SubjectEntity entity, Subject user);
}
