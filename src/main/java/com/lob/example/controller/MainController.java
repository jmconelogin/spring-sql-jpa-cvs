package com.lob.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lob.example.model.ExampleModel;
import com.lob.example.repository.ExampleRepository;

@Controller	
@RequestMapping(path="/demo") 
public class MainController {
	@Autowired
	private ExampleRepository userRepository;
	
	@PostMapping(path="/add") // Map ONLY POST Requests
	public @ResponseBody String addNewUser (@RequestParam String name
			, @RequestParam String email) {
		ExampleModel n = new ExampleModel();
		n.setName(name);
		n.setEmail(email);
		userRepository.save(n);
		return "Saved";
	}

}
