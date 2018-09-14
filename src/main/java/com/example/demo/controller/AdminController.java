package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
	/**
	 *     登录页面
	 * @return
	 */
	@RequestMapping("/login")
	public String userLogin() {
		return "login";
	}
	
	/**
	 * 登录失败页面
	 * @return
	 */
	@RequestMapping("/login-error")
	public String loginError() {
		return "login-error";
	}
}
