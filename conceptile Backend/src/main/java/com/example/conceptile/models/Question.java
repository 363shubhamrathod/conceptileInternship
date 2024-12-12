package com.example.conceptile.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String questionText;

    @Column
    private String A;

    @Column
    private String B;

    @Column
    private String C;

    @Column
    private String D;

    @Column
    @Enumerated(EnumType.STRING)
    private QustionEnum correctAnswer;
}