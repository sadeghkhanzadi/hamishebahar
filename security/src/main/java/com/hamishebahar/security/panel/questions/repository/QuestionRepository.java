package com.hamishebahar.security.panel.questions.repository;

import com.hamishebahar.security.panel.questions.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Questions,Long> {
}
