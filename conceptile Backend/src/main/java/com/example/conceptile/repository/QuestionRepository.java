package com.example.conceptile.repository;

import com.example.conceptile.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuestionRepository extends JpaRepository<Question,Integer> {
    @Query("SELECT q FROM Question q ORDER BY FUNCTION('RAND') LIMIT 1")
    Question findRandomQuestion();
}
