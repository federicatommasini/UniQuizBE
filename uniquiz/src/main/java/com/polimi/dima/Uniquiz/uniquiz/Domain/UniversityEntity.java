package com.polimi.dima.Uniquiz.uniquiz.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "universities")
public class UniversityEntity {

    @Id
    private String id;
    private String name;
    private String location;
    private List<String> subjectIds;
}
