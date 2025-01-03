package com.example.conceptile.controller.v1;

import com.example.conceptile.dto.CorrectnessResponse;
import com.example.conceptile.dto.QuestionOutgoingDto;
import com.example.conceptile.dto.QuizResultOutgoingData;
import com.example.conceptile.dto.SubmitAnswerIncomingData;
import com.example.conceptile.service.v1.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuizController {
    @Autowired
    private QuizService quizService;

    @Operation(summary = "Create new session",description = "Api Endpoint to create new session")
    @GetMapping(path = "/quiz/new")
    public Integer createQuizSession(){
        return quizService.createNewSession();
    }

    @Operation(summary = "Get random question",description = "Api Endpoint to Get random question and validate session")
    @GetMapping(path = "/quiz/session/{sessionId}")
    public QuestionOutgoingDto getRandomQuestion(@PathVariable Integer sessionId){
        return quizService.getRandomQuestion(sessionId);
    }

    @Operation(summary = "Post Answer",description = "Api Endpoint to submit random question and associate it with session")
    @PostMapping(path = "/quiz/session/{sessionId}")
    public CorrectnessResponse submitAnswer(@PathVariable Integer sessionId, @Valid @RequestBody SubmitAnswerIncomingData submitAnswerIncomingData){
        return  quizService.submitAnswer(sessionId,submitAnswerIncomingData);
    }

    @Operation(summary = "Get result",description = "Api Endpoint to get result associate with a session")
    @GetMapping(path = "/quiz/session/{sessionId}/result")
    public QuizResultOutgoingData getResult(@PathVariable Integer sessionId){
        return quizService.getResult(sessionId);
    }
}
