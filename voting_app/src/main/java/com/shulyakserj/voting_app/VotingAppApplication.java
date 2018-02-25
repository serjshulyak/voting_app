package com.shulyakserj.voting_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages="com.shulyakserj.voting_app")
@EnableTransactionManagement

public class VotingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotingAppApplication.class, args);
	}
	
}
