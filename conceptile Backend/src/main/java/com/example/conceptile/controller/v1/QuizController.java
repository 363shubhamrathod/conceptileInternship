package com.example.conceptile.controller.v1;

import com.example.conceptile.dto.CorrectnessResponse;
import com.example.conceptile.dto.QuestionOutgoingDto;
import com.example.conceptile.dto.QuizResultOutgoingData;
import com.example.conceptile.dto.SubmitAnswerIncomingData;
import com.example.conceptile.service.v1.QuizService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuizController {
    @Autowired
    private QuizService quizService;

    @GetMapping(path = "/quiz/new")
    public Integer createQuizSession(){
        return quizService.createNewSession();
    }

    @GetMapping(path = "/quiz/session/{sessionId}")
    public QuestionOutgoingDto getRandomQuestion(@PathVariable Integer sessionId){
        return quizService.getRandomQuestion(sessionId);
    }

    @PostMapping(path = "/quiz/session/{sessionId}")
    public CorrectnessResponse submitAnswer(@PathVariable Integer sessionId, @Valid @RequestBody SubmitAnswerIncomingData submitAnswerIncomingData){
        return  quizService.submitAnswer(sessionId,submitAnswerIncomingData);
    }

    @GetMapping(path = "/quiz/session/{sessionId}/result")
    public QuizResultOutgoingData getResult(@PathVariable Integer sessionId){
        return quizService.getResult(sessionId);
    }
}
