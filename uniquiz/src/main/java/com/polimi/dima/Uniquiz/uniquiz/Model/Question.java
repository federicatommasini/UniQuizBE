package com.polimi.dima.Uniquiz.uniquiz.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private String content;
    private List<Answer> answers;
    private List<String> reports;
}
