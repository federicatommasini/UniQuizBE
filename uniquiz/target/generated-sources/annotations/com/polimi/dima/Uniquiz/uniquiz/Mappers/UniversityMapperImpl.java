package com.polimi.dima.Uniquiz.uniquiz.Mappers;

import com.polimi.dima.Uniquiz.uniquiz.Domain.UniversityEntity;
import com.polimi.dima.Uniquiz.uniquiz.Model.University;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-20T20:48:22+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class UniversityMapperImpl implements UniversityMapper {

    @Override
    public University fromEntity(UniversityEntity entity) {
        if ( entity == null ) {
            return null;
        }

        University.UniversityBuilder university = University.builder();

        if ( entity.getId() != null ) {
            university.id( entity.getId() );
        }
        if ( entity.getName() != null ) {
            university.name( entity.getName() );
        }
        if ( entity.getLocation() != null ) {
            university.location( entity.getLocation() );
        }
        List<String> list = entity.getSubjectIds();
        if ( list != null ) {
            university.subjectIds( new ArrayList<String>( list ) );
        }

        return university.build();
    }

    @Override
    public UniversityEntity toEntity(University model) {
        if ( model == null ) {
            return null;
        }

        UniversityEntity universityEntity = new UniversityEntity();

        if ( model.getId() != null ) {
            universityEntity.setId( model.getId() );
        }
        if ( model.getName() != null ) {
            universityEntity.setName( model.getName() );
        }
        if ( model.getLocation() != null ) {
            universityEntity.setLocation( model.getLocation() );
        }
        List<String> list = model.getSubjectIds();
        if ( list != null ) {
            universityEntity.setSubjectIds( new ArrayList<String>( list ) );
        }

        return universityEntity;
    }
}
