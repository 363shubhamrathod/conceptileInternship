package com.example.conceptile.dto;

import com.example.conceptile.models.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionOutgoingDto {
    private Integer id;
    private String questionText;
    private String A;
    private String B;
    private String C;
    private String D;

    public QuestionOutgoingDto(Question question) {
        this.id=question.getId();
        this.questionText=question.getQuestionText();
        this.A=question.getA();
        this.B=question.getB();
        this.C=question.getC();
        this.D=question.getD();
    }
}
