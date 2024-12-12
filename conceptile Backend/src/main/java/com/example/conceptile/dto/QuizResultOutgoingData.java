package com.example.conceptile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizResultOutgoingData {
    private Integer totalAttempted;
    private Integer correctAnswer;
    private Integer wrongAnswer;
}
