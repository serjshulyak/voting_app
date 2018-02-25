package com.shulyakserj.voting_app.services;

import java.io.Serializable;
import java.util.List;

import com.shulyakserj.voting_app.VotingException;
import com.shulyakserj.voting_app.entity.Subject;


public interface SubjectService extends Serializable {
	Subject create_subject(String title) throws VotingException;
	Subject findById(long id) throws VotingException;
	List<Subject> findAll();
}
