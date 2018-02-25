package com.shulyakserj.voting_app.repository;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shulyakserj.voting_app.entity.Voting;

@PersistenceContext(name = "voting_app_pu")
public interface VotingRepository extends JpaRepository<Voting, Long> {
	
	@Query("select v.question, v.url from Voting v ")
	List<Voting> findAll(); 
	
	@Query(value = "select v from Voting v where v.subject.id=?1")
	List<Voting> findAllVotingsOfSubject(long subject_id);
	
	@Query(value="select v from Voting v where v.voting_id=?1")
	Voting findById(long id);
	
	@Query(value = "select v.voting_id from Voting v where v.subject.id=?1")
	List<Long> allSubjectVotingId(long subject_id);
	
	@Query(value = "select v from Voting v where v.url like ?1")
	Voting findByUrl(String voting_url);
}
