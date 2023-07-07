package com.polimi.dima.Uniquiz.uniquiz.Service;

import com.polimi.dima.Uniquiz.uniquiz.Domain.ExamEntity;
import com.polimi.dima.Uniquiz.uniquiz.Domain.UserEntity;
import com.polimi.dima.Uniquiz.uniquiz.Mappers.ExamMapper;
import com.polimi.dima.Uniquiz.uniquiz.Mappers.UserMapper;
import com.polimi.dima.Uniquiz.uniquiz.Model.Exam;
import com.polimi.dima.Uniquiz.uniquiz.Model.ExamRequest;
import com.polimi.dima.Uniquiz.uniquiz.Model.User;
import com.polimi.dima.Uniquiz.uniquiz.Repository.ExamRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExamService {

    private ExamRepository repository;
    private UserService userService;

    public List<Exam> getExams(){
        List<ExamEntity> exams = repository.findAll();
        return exams.stream().map(ExamMapper.INSTANCE::fromEntity).collect(Collectors.toList());
    }


    public User addExam(String userId, ExamRequest examRequest) {
        Exam exam = new Exam();
        exam.setSubjectId(examRequest.getSubjectId());
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = formatter.parse(examRequest.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        exam.setDate(date);
        System.out.println(exam.getDate().toString());
        Boolean toSave = true;
        Exam savedExam = null;
        //check if the exam already exists
        for(Exam e : getExams()){
            if(e.getSubjectId().compareTo(exam.getSubjectId()) == 0 && e.getDate().compareTo(exam.getDate()) == 0){
                toSave = false;
                savedExam = e;
            }
        }
        //if it does not exist, save it and then add it to the user
        if(toSave){
            ExamEntity entity = ExamMapper.INSTANCE.toEntity(exam);
            ExamEntity savedEntity = repository.save(entity);
            savedExam = ExamMapper.INSTANCE.fromEntity(savedEntity); 
        }        
        return userService.addExam(savedExam, userId);
    }
}
