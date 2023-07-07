package com.polimi.dima.Uniquiz.uniquiz.Service;

import com.polimi.dima.Uniquiz.uniquiz.Domain.ExamEntity;
import com.polimi.dima.Uniquiz.uniquiz.Mappers.ExamMapper;
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

@Service
@AllArgsConstructor
public class ExamService {

    private ExamRepository repository;
    private UserService userService;


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
        ExamEntity entity = ExamMapper.INSTANCE.toEntity(exam);
        ExamEntity savedEntity = repository.save(entity);
        Exam savedExam = ExamMapper.INSTANCE.fromEntity(savedEntity);
        return userService.addExam(savedExam, userId);
    }
}
