package com.example.fjtravel.controller;

import com.example.fjtravel.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge=3600)
@RequestMapping("/fjtravel/question")
public class QuestionController {
	
	@Autowired
	QuestionService questService;
	
	@RequestMapping("/query")
	public String query(@RequestParam(value = "question") String question) throws Exception {
		return questService.answer(question);
	}
	
	@RequestMapping("/path")
	public void checkPath(){
		questService.showDictPath();
	}
}
