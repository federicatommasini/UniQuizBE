package com.polimi.dima.Uniquiz.uniquiz.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Group {
    @Id
    private String id;
    private String subjectId;
    private Map<String, Integer> ranking;
}
