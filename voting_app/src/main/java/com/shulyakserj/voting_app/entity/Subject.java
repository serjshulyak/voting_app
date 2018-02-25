package com.shulyakserj.voting_app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="subject")
public class Subject {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column
	private String text;

	public Long getSubject_id() {
		return id;
	} 

	public void setSubject_id(Long subject_id) {
		this.id = subject_id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", text=" + text + "]";
	}
	
	
}
