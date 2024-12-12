package com.example.conceptile.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToMany(mappedBy = "session", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubmittedAnswer> submittedAnswers = new ArrayList<>();
}
