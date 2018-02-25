package com.shulyakserj.voting_app.repository;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shulyakserj.voting_app.entity.Subject;

@PersistenceContext(name = "voting_app_pu")
public interface SubjectRepository extends JpaRepository<Subject, Long> {
	
	List<Subject> findAll();
	Subject findById(long id);
	
}
