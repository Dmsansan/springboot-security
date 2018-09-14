package com.example.demo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
	@RequestMapping("/index")
	public String index() {
		return "spring security index";
	}
	
	@RequestMapping("/whoim")
	public Object whoim() {
		return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
