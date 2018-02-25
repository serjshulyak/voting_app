package com.shulyakserj.voting_app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "answer")
public class Answer {
	@Id
	@GeneratedValue
	@Column(name = "answer_id")
	private Long answer_id;
	
	@Column(name = "answer_text", nullable=false)
	private String answer_text;
	
	@Column(name = "answer_votes")
	private Long answer_votes = 0L;
	
	@Column(name = "voting_id", nullable=false)
	private Long voting_id;

	public Long getAnswer_id() {
		return answer_id;
	}

	public void setAnswer_id(Long answer_id) {
		this.answer_id = answer_id;
	}

	public String getAnswer_text() {
		return answer_text;
	}

	public void setAnswer_text(String answer_text) {
		this.answer_text = answer_text;
	}

	public Long getAnswer_votes() {
		return answer_votes;
	}

	public void setAnswer_votes(Long answer_votes) {
		this.answer_votes = answer_votes;
	}

	public Long getVoting_id() {
		return voting_id;
	}

	public void setVoting_id(Long voting_id) {
		this.voting_id = voting_id;
	}

	@Override
	public String toString() {
		return "Answer [answer_id=" + answer_id + ", answer_text=" + answer_text + ", answer_votes=" + answer_votes
				+ ", voting_id=" + voting_id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer_id == null) ? 0 : answer_id.hashCode());
		result = prime * result + ((answer_text == null) ? 0 : answer_text.hashCode());
		result = prime * result + ((answer_votes == null) ? 0 : answer_votes.hashCode());
		result = prime * result + ((voting_id == null) ? 0 : voting_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Answer other = (Answer) obj;
		if (answer_id == null) {
			if (other.answer_id != null)
				return false;
		} else if (!answer_id.equals(other.answer_id))
			return false;
		if (answer_text == null) {
			if (other.answer_text != null)
				return false;
		} else if (!answer_text.equals(other.answer_text))
			return false;
		if (answer_votes == null) {
			if (other.answer_votes != null)
				return false;
		} else if (!answer_votes.equals(other.answer_votes))
			return false;
		if (voting_id == null) {
			if (other.voting_id != null)
				return false;
		} else if (!voting_id.equals(other.voting_id))
			return false;
		return true;
	}

	public void incrementVotes() {
		this.answer_votes++;
	}
	
	
}
