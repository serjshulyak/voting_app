package com.shulyakserj.voting_app.rest_controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.shulyakserj.voting_app.VotingException;
import com.shulyakserj.voting_app.entity.Answer;
import com.shulyakserj.voting_app.entity.Subject;
import com.shulyakserj.voting_app.entity.Voting;
import com.shulyakserj.voting_app.services.PollService;
import com.shulyakserj.voting_app.services.SubjectService;

@RestController
@RequestMapping("api")
public class VotingAppRestController {
	@Autowired
	private SubjectService mSubjectService;
	@Autowired
	private PollService mPollService;

	@PostMapping(value = "/create_subject", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> create_subject(@RequestParam(value = "title") String title, UriComponentsBuilder URIComponentsBuilder) throws VotingException {
		final Subject subj = mSubjectService.create_subject(title);

		HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>(subj, responseHeaders, HttpStatus.OK);
	}

	@GetMapping(value = "/{voting_url}/get_voting", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Voting subject_votings_id(@PathVariable String voting_url) throws VotingException {
		return mPollService.findVotingByUrl(voting_url);
	}

	@PutMapping(value = "/{voting_url}/add_answer", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> add_answer(@PathVariable String voting_url,
			@RequestParam(value = "text") String text) throws VotingException {
		final Answer answer =  mPollService.addAnwerToVoting(voting_url, text);
		
		HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>(answer, responseHeaders, HttpStatus.OK);
	}

	@GetMapping(value = "/{voting_url}/voting_answers", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Answer> voting_answers(@PathVariable String voting_url) throws VotingException {

		return mPollService.findAllVotingAnswers(voting_url);
	}

	@PutMapping(value = "/increment_vote", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> increment_vote(@RequestParam(value = "answer_id") Long answer_id) throws VotingException {
		final Answer answer  = mPollService.incrementVote(answer_id);
		
		HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>(answer, responseHeaders, HttpStatus.OK);
	}

	@PostMapping(value = "/{voting_url}/set_voting_active", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> set_subject_active(@PathVariable String voting_url,
			@RequestParam(value = "active") boolean active) throws VotingException {
		final Voting voting =  mPollService.setVotingActiveState(voting_url, active);
		
		HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>(voting, responseHeaders, HttpStatus.OK);
	}

	@GetMapping(value = "/all_subjects", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Subject> all_subjects() {
		return mSubjectService.findAll();
	}

	@GetMapping(value = "/all_votings", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Voting> all_votings() {
		return mPollService.findAllVotings();
	}

	@GetMapping(value = "/all_answers", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Answer> all_answers() {
		return mPollService.findAllAnswers();
	}

	@GetMapping("/subject_votings")
	public List<Voting> subject_votings(@RequestParam(value = "subject_id") long subject_id) {
		return mPollService.getSubjectVotings(subject_id);
	}

	@PostMapping("/create_voting")
	public ResponseEntity<?> create_voting(@RequestParam(value = "subject_id") long subject_id,
			@RequestParam(value = "question") String question) throws VotingException {
		final Subject subj = mSubjectService.findById(subject_id);
		final Voting voting =  mPollService.createVoting(subj, question);
		
		HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>(voting, responseHeaders, HttpStatus.OK);
	}

}
