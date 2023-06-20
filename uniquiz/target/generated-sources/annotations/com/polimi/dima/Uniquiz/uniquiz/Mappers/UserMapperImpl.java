package com.polimi.dima.Uniquiz.uniquiz.Mappers;

import com.polimi.dima.Uniquiz.uniquiz.Domain.UserEntity;
import com.polimi.dima.Uniquiz.uniquiz.Model.Exam;
import com.polimi.dima.Uniquiz.uniquiz.Model.Schedule;
import com.polimi.dima.Uniquiz.uniquiz.Model.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-20T20:48:22+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public User fromEntity(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        if ( entity.getId() != null ) {
            user.id( entity.getId() );
        }
        if ( entity.getUsername() != null ) {
            user.username( entity.getUsername() );
        }
        if ( entity.getEmail() != null ) {
            user.email( entity.getEmail() );
        }
        if ( entity.getPassword() != null ) {
            user.password( entity.getPassword() );
        }
        if ( entity.getFirstName() != null ) {
            user.firstName( entity.getFirstName() );
        }
        if ( entity.getLastName() != null ) {
            user.lastName( entity.getLastName() );
        }
        if ( entity.getUniversityId() != null ) {
            user.universityId( entity.getUniversityId() );
        }
        List<String> list = entity.getSubjectIds();
        if ( list != null ) {
            user.subjectIds( new ArrayList<String>( list ) );
        }
        List<Exam> list1 = entity.getExams();
        if ( list1 != null ) {
            user.exams( new ArrayList<Exam>( list1 ) );
        }
        List<Schedule> list2 = entity.getSchedules();
        if ( list2 != null ) {
            user.schedules( new ArrayList<Schedule>( list2 ) );
        }

        return user.build();
    }

    @Override
    public UserEntity toEntity(User model) {
        if ( model == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        if ( model.getId() != null ) {
            userEntity.setId( model.getId() );
        }
        if ( model.getUsername() != null ) {
            userEntity.setUsername( model.getUsername() );
        }
        if ( model.getEmail() != null ) {
            userEntity.setEmail( model.getEmail() );
        }
        if ( model.getPassword() != null ) {
            userEntity.setPassword( model.getPassword() );
        }
        if ( model.getFirstName() != null ) {
            userEntity.setFirstName( model.getFirstName() );
        }
        if ( model.getLastName() != null ) {
            userEntity.setLastName( model.getLastName() );
        }
        if ( model.getUniversityId() != null ) {
            userEntity.setUniversityId( model.getUniversityId() );
        }
        List<String> list = model.getSubjectIds();
        if ( list != null ) {
            userEntity.setSubjectIds( new ArrayList<String>( list ) );
        }
        List<Exam> list1 = model.getExams();
        if ( list1 != null ) {
            userEntity.setExams( new ArrayList<Exam>( list1 ) );
        }
        List<Schedule> list2 = model.getSchedules();
        if ( list2 != null ) {
            userEntity.setSchedules( new ArrayList<Schedule>( list2 ) );
        }

        return userEntity;
    }
}
