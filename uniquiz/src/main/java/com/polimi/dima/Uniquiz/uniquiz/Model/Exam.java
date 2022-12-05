package com.polimi.dima.Uniquiz.uniquiz.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Exam {
    @Id
    private String id;
    private String subjectId;
    private Date date;
}
