package com.polimi.dima.Uniquiz.uniquiz.Domain;

import com.polimi.dima.Uniquiz.uniquiz.Model.Exam;
import com.polimi.dima.Uniquiz.uniquiz.Model.Schedule;
import com.polimi.dima.Uniquiz.uniquiz.Model.UserExam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class UserEntity {
    @Id
    private String id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String universityId;
    private List<String> subjectIds;
    private List<UserExam> exams;
    private List<Schedule> schedules;
    private String profilePicUrl;
    private int questionsAdded;
    private int questionsReported;
}
