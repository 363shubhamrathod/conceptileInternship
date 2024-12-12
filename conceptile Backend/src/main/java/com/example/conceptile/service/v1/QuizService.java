package com.example.conceptile.service.v1;

import com.example.conceptile.dto.CorrectnessResponse;
import com.example.conceptile.dto.QuestionOutgoingDto;
import com.example.conceptile.dto.QuizResultOutgoingData;
import com.example.conceptile.dto.SubmitAnswerIncomingData;
import com.example.conceptile.exceptions.GenericExceptionHandler;
import com.example.conceptile.models.Question;
import com.example.conceptile.models.QustionEnum;
import com.example.conceptile.models.Session;
import com.example.conceptile.models.SubmittedAnswer;
import com.example.conceptile.repository.QuestionRepository;
import com.example.conceptile.repository.SessionRepository;
import com.example.conceptile.repository.SubmittedAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizService {
    @Autowired
    public QuestionRepository questionRepository;

    @Autowired
    public SessionRepository sessionRepository;

    @Autowired
    public SubmittedAnswerRepository submittedAnswerRepository;

    public Integer createNewSession() {
        Session session = new Session();
        session=sessionRepository.save(session);
        return session.getId();
    }


    public QuestionOutgoingDto getRandomQuestion(Integer sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new GenericExceptionHandler("Session does not exist"));
        Question question = questionRepository.findRandomQuestion();
        return new QuestionOutgoingDto(question);
    }


    public CorrectnessResponse submitAnswer(Integer sessionId, SubmitAnswerIncomingData submitAnswerIncomingData) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new GenericExceptionHandler("Session does not exist"));
        Question question = questionRepository.findById(submitAnswerIncomingData.questionId())
                .orElseThrow(() -> new GenericExceptionHandler("Question does not exist"));
        SubmittedAnswer submittedAnswer = SubmittedAnswer.builder()
                .question(question)
                .session(session)
                .givenAnswer(submitAnswerIncomingData.qustionEnum())
                .build();
        submittedAnswerRepository.save(submittedAnswer);
        return new CorrectnessResponse(question.getCorrectAnswer()==submitAnswerIncomingData.qustionEnum());
    }

    public QuizResultOutgoingData getResult(Integer sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new GenericExceptionHandler("Session does not exist"));
        System.out.println(session.getSubmittedAnswers().size());
        int totalAttempted = session.getSubmittedAnswers().size();
        int correctAnswers = 0;
        int wrongAnswers = 0;

        // Iterate through the submitted answers to count correct and wrong answers
        for (SubmittedAnswer submittedAnswer : session.getSubmittedAnswers()) {
            Question question = submittedAnswer.getQuestion();
            QustionEnum userAnswer = submittedAnswer.getGivenAnswer();  // The user's answer as QustionEnum
            QustionEnum correctAnswer = question.getCorrectAnswer();  // Correct answer as QustionEnum

            if (correctAnswer.equals(userAnswer)) {
                correctAnswers++;
            } else {
                wrongAnswers++;
            }
        }

        // Create the response DTO
        QuizResultOutgoingData quizResultOutgoingData = new QuizResultOutgoingData();
        quizResultOutgoingData.setTotalAttempted(totalAttempted);
        quizResultOutgoingData.setCorrectAnswer(correctAnswers);
        quizResultOutgoingData.setWrongAnswer(wrongAnswers);

        return quizResultOutgoingData;
    }
}
