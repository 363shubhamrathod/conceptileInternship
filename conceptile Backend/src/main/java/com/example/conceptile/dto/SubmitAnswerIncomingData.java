package com.example.conceptile.dto;

import com.example.conceptile.models.QustionEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public record SubmitAnswerIncomingData(
        @NotNull(message = "QuestionId must be present")
        Integer questionId,
        @NotNull(message = "Answer Enum must be present")
        @Enumerated(EnumType.STRING)
        QustionEnum qustionEnum
) {
}
