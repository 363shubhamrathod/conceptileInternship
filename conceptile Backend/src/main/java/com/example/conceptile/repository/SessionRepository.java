package com.example.conceptile.repository;

import com.example.conceptile.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session,Integer> {
}
