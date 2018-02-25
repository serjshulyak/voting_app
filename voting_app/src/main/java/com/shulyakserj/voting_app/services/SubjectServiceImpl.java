package com.shulyakserj.voting_app.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shulyakserj.voting_app.VotingException;
import com.shulyakserj.voting_app.entity.Subject;
import com.shulyakserj.voting_app.repository.SubjectRepository;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService{
	private static final long serialVersionUID = 1L;
	@Autowired
	private SubjectRepository mSubjectRepository;
	

	public List<Subject> findAll(){
		return mSubjectRepository.findAll();
	}

	public Subject create_subject(String title) throws VotingException {
		if(title == null || title.isEmpty()) {
			throw new VotingException("Parameter title is empty!");
		}
		final Subject newSubject = new Subject();
		newSubject.setText(title);
		return mSubjectRepository.save(newSubject);
		
	}

	public Subject findById(long id) {
		return mSubjectRepository.findById(id);
	}

}
