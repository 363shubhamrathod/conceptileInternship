package com.example.conceptile.controller.v1;

import com.example.conceptile.dto.CorrectnessResponse;
import com.example.conceptile.dto.QuestionOutgoingDto;
import com.example.conceptile.dto.QuizResultOutgoingData;
import com.example.conceptile.dto.SubmitAnswerIncomingData;
import com.example.conceptile.service.v1.Quiz;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class quiz {
    @Autowired
    private Quiz quiz;

    @GetMapping(path = "/quiz/new")
    public Integer createQuizSession(){
        return quiz.createNewSession();
    }

    @GetMapping(path = "/quiz/session/{sessionId}")
    public QuestionOutgoingDto getRandomQuestion(@PathVariable Integer sessionId){
        return quiz.getRandomQuestion(sessionId);
    }

    @PostMapping(path = "/quiz/session/{sessionId}")
    public CorrectnessResponse submitAnswer(@PathVariable Integer sessionId, @Valid @RequestBody SubmitAnswerIncomingData submitAnswerIncomingData){
        return  quiz.submitAnswer(sessionId,submitAnswerIncomingData);
    }

    @GetMapping(path = "/quiz/session/{sessionId}/result")
    public QuizResultOutgoingData getResult(@PathVariable Integer sessionId){
        return quiz.getResult(sessionId);
    }
}
