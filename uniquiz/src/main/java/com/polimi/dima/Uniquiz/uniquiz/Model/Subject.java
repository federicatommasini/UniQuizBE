package com.polimi.dima.Uniquiz.uniquiz.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
    @Id
    private String id;
    private String code;
    private String name;
    private List<String> quizIds;
    private Map<String, Integer> ranking;
    private String base_url;
    private List<String> pdf_links;
}
