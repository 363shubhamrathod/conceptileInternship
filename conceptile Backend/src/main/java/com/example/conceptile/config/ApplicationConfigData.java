package com.example.conceptile.config;

import com.example.conceptile.models.Question;
import com.example.conceptile.models.QustionEnum;
import com.example.conceptile.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import java.nio.file.Files;
import java.nio.file.Path;

@Profile("dev") // Ensures these beans only run in the 'dev' profile
@Configuration
public class ApplicationConfigData {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * CommandLineRunner to pre-load Question data into the database
     */
    @Bean
    CommandLineRunner commandLineRunner(QuestionRepository questionRepository) {
        return args -> {
            Question question1 = Question.builder()
                    .questionText("What is the capital of France?")
                    .A("Paris")
                    .B("Berlin")
                    .C("Madrid")
                    .D("Rome")
                    .correctAnswer(QustionEnum.A)
                    .build();

            Question question2 = Question.builder()
                    .questionText("Which planet is known as the Red Planet?")
                    .A("Earth")
                    .B("Mars")
                    .C("Jupiter")
                    .D("Saturn")
                    .correctAnswer(QustionEnum.B)
                    .build();

            Question question3 = Question.builder()
                    .questionText("What is the largest ocean on Earth?")
                    .A("Atlantic Ocean")
                    .B("Indian Ocean")
                    .C("Arctic Ocean")
                    .D("Pacific Ocean")
                    .correctAnswer(QustionEnum.D)
                    .build();

            Question question4 = Question.builder()
                    .questionText("Who wrote 'Romeo and Juliet'?")
                    .A("Charles Dickens")
                    .B("William Shakespeare")
                    .C("Jane Austen")
                    .D("Mark Twain")
                    .correctAnswer(QustionEnum.B)
                    .build();

            // Save all questions to the database
            questionRepository.save(question1);
            questionRepository.save(question2);
            questionRepository.save(question3);
            questionRepository.save(question4);
        };
    }

    /**
     * CommandLineRunner to execute the data.sql script after the application starts
     */
    @Bean
    CommandLineRunner runDataSql() {
        return args -> {
            try {
                // Read and execute the data.sql file
                String sql = Files.readString(Path.of("src/main/resources/data.sql"));
                jdbcTemplate.execute(sql);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to execute data.sql", e);
            }
        };
    }
}
