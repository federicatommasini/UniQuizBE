package com.polimi.dima.Uniquiz.uniquiz.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "exams")
public class ExamEntity {
    @Id
    private String id;
    private String subjectId;
    private Date date;
}
