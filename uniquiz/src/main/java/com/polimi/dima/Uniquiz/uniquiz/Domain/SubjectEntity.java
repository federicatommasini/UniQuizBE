package com.polimi.dima.Uniquiz.uniquiz.Domain;

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
@Document(collection = "subjects")
public class SubjectEntity {

    @Id
    private String id;
    private String code;
    private String name;
    private List<String> quizIds;
    private Map<String, Integer> ranking;
}
