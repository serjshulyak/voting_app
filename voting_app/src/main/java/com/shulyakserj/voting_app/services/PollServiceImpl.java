package com.shulyakserj.voting_app.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shulyakserj.voting_app.VotingException;
import com.shulyakserj.voting_app.entity.Answer;
import com.shulyakserj.voting_app.entity.Subject;
import com.shulyakserj.voting_app.entity.Voting;
import com.shulyakserj.voting_app.repository.AnswerRepository;
import com.shulyakserj.voting_app.repository.VotingRepository;
@Service
@Transactional
public class PollServiceImpl implements PollService {

	private static final long serialVersionUID = 1L;
	@Autowired
	private VotingRepository mVotingRepository;
	@Autowired
	private AnswerRepository mAnswerRepository;

	public Voting findVotingByUrl(String url) {
		return mVotingRepository.findByUrl(url);
	}
	public Answer addAnwerToVoting(String voting_url, String text) throws VotingException {
		final Voting voting = mVotingRepository.findByUrl(voting_url);
		if(voting == null) {
			throw new VotingException(  String.format("Cant find voting with URL %1$s", voting_url));
		}
		if(text.isEmpty()) {
			throw new VotingException( "Answer is empty");
		}
		final Answer answer = new Answer();
		
		answer.setVoting_id(voting.getVoting_id());
		answer.setAnswer_text(text);
		voting.addAnswer(answer);
		
		mAnswerRepository.save(answer);
		mVotingRepository.save(voting);
		return answer;
	}
	public List<Answer> findAllVotingAnswers(String voting_url) throws VotingException{
		final Voting voting = mVotingRepository.findByUrl(voting_url);
		if(voting == null) {
			throw new VotingException(  String.format("Cant find voting with URL %1$s", voting_url));
		}
		
		return voting.getAnswers();
	}
	public Answer incrementVote(Long answer_id) throws VotingException{
		final Answer answer = mAnswerRepository.findById(answer_id);
		final Voting voting = mVotingRepository.findById(answer.getVoting_id());
		
		if(answer == null) {
			throw new VotingException(String.format("Cannot find answer with id %1$s", answer_id));
		}
		if(voting == null) {
			throw new VotingException(String.format("Cannot find voting for ansver id %1$s", answer_id));
		}
		if(!voting.isEnabled()) {
			throw new VotingException("Voting is closed!");
		}
		
		answer.incrementVotes();
		mAnswerRepository.save(answer);
		return answer;
	}
	public Voting setVotingActiveState(String voting_url, boolean active) throws VotingException{
		final Voting voting = mVotingRepository.findByUrl(voting_url);
		if(voting == null) {
			throw new VotingException(  String.format("Cannot find voting with URL %1$s", voting_url));
		}
		voting.setEnabled(active);
		mVotingRepository.save(voting);
		return voting;
	}
	public Voting createVoting(Subject subject,String question)throws VotingException{

		final Voting voting = new Voting();
		voting.setEnabled(false);
		voting.setQuestion(question);
		voting.setSubject(subject);
		mVotingRepository.save(voting);

		return voting;
	}
	@Override
	public List<Voting> findAllVotings() {
		return mVotingRepository.findAll();
	}
	@Override
	public List<Answer> findAllAnswers() {
		return mAnswerRepository.findAll();
	}
	@Override
	public List<Voting> getSubjectVotings(long subject_id) {
		return mVotingRepository.findAllVotingsOfSubject(subject_id);
	}
}
