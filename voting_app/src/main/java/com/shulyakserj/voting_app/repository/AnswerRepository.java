package com.shulyakserj.voting_app.repository;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shulyakserj.voting_app.entity.Answer;

@PersistenceContext(name = "voting_app_pu")
public interface AnswerRepository extends JpaRepository<Answer, Long> {
	List<Answer> findAll();

	@Query("select a from Answer a where a.answer_id=?1")
	Answer findById(Long answer_id);
}
