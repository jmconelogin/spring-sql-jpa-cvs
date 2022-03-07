package com.lob.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lob.example.model.ExampleModel;
import com.lob.example.repository.ExampleRepository;

@Controller	
@RequestMapping(path="/demo") 
public class MainController {
	@Autowired
	private ExampleRepository exampleRepository;
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<ExampleModel> getAllUsers() {
		// This returns a JSON or XML with the users
		return exampleRepository.findAll();
	}

}
