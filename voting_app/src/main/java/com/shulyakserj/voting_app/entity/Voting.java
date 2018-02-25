package com.shulyakserj.voting_app.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="voting")
public class Voting {
	public Voting() {
		this.url = UUID.randomUUID().toString();
	}
	@Id
	@GeneratedValue
	@Column(name = "voting_id")
	private Long voting_id;
	
	@Column(name="voting_question", nullable=false)
	private String question;
	
	@ManyToOne
	@JoinColumn(name = "subject_id", referencedColumnName = "id", nullable=false)
	private Subject subject;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="answers",  referencedColumnName = "voting_id")
	private List<Answer> answers;
	
	@Column(name="voting_enabled")
	private boolean enabled;
	

	@Column(name = "voting_url", nullable=false)
	private String url;
	
	public Long getVoting_id() {
		return voting_id;
	}

	public void setVoting_id(Long voting_id) {
		this.voting_id = voting_id;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void addAnswer(Answer answer) {
		if(answers == null)
			answers = new ArrayList<Answer>();
			
		answers.add(answer);
		
	}


	
}
