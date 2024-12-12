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
public class SubmittedAnswer {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private QustionEnum givenAnswer;

    @OneToOne
    @JoinColumn(nullable = false)
    private Question question;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Session session;
}
