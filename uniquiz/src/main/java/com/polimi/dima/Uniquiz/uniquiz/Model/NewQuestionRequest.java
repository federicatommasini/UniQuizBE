package com.polimi.dima.Uniquiz.uniquiz.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewQuestionRequest {
    private String userId;
    private String subjectId;
    private String quizId;
    private String quizName;
    private Question question;
}
