package com.polimi.dima.Uniquiz.uniquiz.Domain;

import com.polimi.dima.Uniquiz.uniquiz.Model.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "quizzes")
public class QuizEntity {

    @Id
    private String id;
    private String name;
    private List<Question> questions;
    private Map<String,Integer> score;
}
