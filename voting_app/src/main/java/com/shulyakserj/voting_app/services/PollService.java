package com.shulyakserj.voting_app.services;

import java.io.Serializable;
import java.util.List;

import com.shulyakserj.voting_app.VotingException;
import com.shulyakserj.voting_app.entity.Answer;
import com.shulyakserj.voting_app.entity.Subject;
import com.shulyakserj.voting_app.entity.Voting;

public interface PollService extends Serializable {
	Voting findVotingByUrl(String url) throws VotingException;
	Answer addAnwerToVoting(String voting_url, String text) throws VotingException;
	List<Answer> findAllVotingAnswers(String voting_url) throws VotingException;
	Answer incrementVote(Long answer_id) throws VotingException;
	Voting setVotingActiveState(String voting_url, boolean active) throws VotingException;
	List<Voting> findAllVotings();
	List<Answer> findAllAnswers();
	List<Voting> getSubjectVotings(long subject_id);
	Voting createVoting(Subject subject,String question)throws VotingException;
}
