package com.myweb.www.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myweb.www.domain.UserVO;
import com.myweb.www.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/user/*")
@Slf4j
@Controller
public class UserController {
	@Inject
	private UserService usv;
	
	@GetMapping("/login")
	public String getLogin() {
		return "/user/login";
	}
	
	public String postLogin(UserVO uvo, HttpServletRequest request, Model m) {
		UserVO login = usv.login(uvo);
		
		if(login!=null) {
			HttpSession ses = request.getSession();
			ses.setAttribute("ses", ses);
			ses.setMaxInactiveInterval(60*10);
			m.addAttribute("login", login);
		}
		return "home";
	}
	
	@GetMapping("/signup")
	public String getSignup() {
		return "/user/signup";
	}
	
	@PostMapping("/signup")
	public String postSignup(UserVO uvo) {
		int isOk = usv.signup(uvo);
		return "/user/login";
	}
	
	@GetMapping("/modify")
	public String getModify(@RequestParam("id")String id) {
		return "/user/modify";
	}
}
