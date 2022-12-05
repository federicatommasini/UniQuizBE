package com.polimi.dima.Uniquiz.uniquiz.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "groups")
public class GroupEntity {
    @Id
    private String id;
    private String subjectId;
    private Map<String, Integer> ranking;
}
