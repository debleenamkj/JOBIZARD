package com.stackroute.Assessmentservice.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {
    private int qid;
    private String quizTitle;
    private int numberOfQuestions;
    private List<Questions> questions;
    private List<String> answers;

}