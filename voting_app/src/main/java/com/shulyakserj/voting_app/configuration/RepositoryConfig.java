package com.shulyakserj.voting_app.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.shulyakserj.voting_app.entity.Answer;
import com.shulyakserj.voting_app.entity.Subject;
import com.shulyakserj.voting_app.entity.Voting;

@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {
    @Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
    	config.exposeIdsFor(Subject.class);
    	config.exposeIdsFor(Voting.class);
        config.exposeIdsFor(Answer.class);
    }
}