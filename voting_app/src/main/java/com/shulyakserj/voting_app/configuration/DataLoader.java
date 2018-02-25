package com.shulyakserj.voting_app.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.shulyakserj.voting_app.entity.Subject;
import com.shulyakserj.voting_app.entity.Voting;
import com.shulyakserj.voting_app.services.PollService;
import com.shulyakserj.voting_app.services.SubjectService;

@Component
public class DataLoader implements ApplicationRunner {

	private final PollService pollService;
	private final SubjectService  subjectService;
	
	@Autowired
	public DataLoader(PollService pollService, SubjectService  subjectService) {
		this.pollService = pollService;
		this.subjectService = subjectService;
	}

	public void run(ApplicationArguments args) {
		System.out.println("---- run --------");
		if(subjectService.findAll().isEmpty()){
			try {
				Subject subj1 = subjectService.create_subject("Test subject");
				Voting vot1 = pollService.createVoting(subj1, "question for voting 1");
				pollService.addAnwerToVoting(vot1.getUrl(), "option_1");
				pollService.addAnwerToVoting(vot1.getUrl(), "option_2");
				pollService.addAnwerToVoting(vot1.getUrl(), "option_3");
				pollService.setVotingActiveState(vot1.getUrl(), true);
				
				Voting vot2 = pollService.createVoting(subj1, "question for voting 2");
				pollService.addAnwerToVoting(vot2.getUrl(), "another voting option 1");
				pollService.addAnwerToVoting(vot2.getUrl(), "another voting option 2");
				pollService.addAnwerToVoting(vot2.getUrl(), "another voting option 3");
				pollService.addAnwerToVoting(vot2.getUrl(), "another voting option 4");
				pollService.setVotingActiveState(vot2.getUrl(), true);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
