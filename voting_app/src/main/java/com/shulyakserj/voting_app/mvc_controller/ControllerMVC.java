package com.shulyakserj.voting_app.mvc_controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControllerMVC {

	@RequestMapping("/")
	public String root(HttpServletRequest request) {
		return "index";
	}
	@RequestMapping("/subject_{subject_id}")
	public String subject(@PathVariable Long subject_id, Model model) {
		
		model.addAttribute("subject_id", subject_id);
		return "subject";
	}
	@RequestMapping("/votings/{url}")
	public String subject(@PathVariable String url, Model model) {
		model.addAttribute("voting_url", url);
		return "voting";
	}
}
