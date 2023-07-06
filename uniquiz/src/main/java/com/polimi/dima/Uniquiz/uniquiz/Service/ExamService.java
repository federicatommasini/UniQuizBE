package com.polimi.dima.Uniquiz.uniquiz.Service;

import com.polimi.dima.Uniquiz.uniquiz.Mappers.ExamMapper;
import com.polimi.dima.Uniquiz.uniquiz.Mappers.UserMapper;
import com.polimi.dima.Uniquiz.uniquiz.Model.Exam;
import com.polimi.dima.Uniquiz.uniquiz.Model.ExamRequest;
import com.polimi.dima.Uniquiz.uniquiz.Model.User;
import com.polimi.dima.Uniquiz.uniquiz.Repository.ExamRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExamService {

    private ExamRepository repository;
    private UserService userService;


    public User addExam(String userId, ExamRequest fakeExam) {
        val exam = new Exam();
        exam.setSubjectId(fakeExam.getSubjectId());
        exam.setDate(fakeExam.getDate());
        var entity = ExamMapper.INSTANCE.toEntity(exam);
        var savedEntity = repository.save(entity);
        val savedExam = ExamMapper.INSTANCE.fromEntity(savedEntity);
        return userService.addExam(savedExam, userId);
    }
}
