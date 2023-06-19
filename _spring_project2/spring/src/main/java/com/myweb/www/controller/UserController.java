package com.myweb.www.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myweb.www.domain.UserVO;
import com.myweb.www.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/member/*")
@Controller
public class UserController {
	@Inject
	private UserService usv;
	
	@GetMapping("/login")
	public String getLogin() {
		return "/user/login";
	}
	
	@PostMapping("/login")
	public String postLogin(String id, String pw, HttpServletRequest request, Model m) {
		UserVO login = usv.login(id,pw);
		if(login != null) {
			HttpSession ses = request.getSession();
			ses.setAttribute("ses", login);
			m.addAttribute("login", login);
		}
		return "home";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("ses");
		request.getSession().invalidate();
		return "home";
	}
	
	@GetMapping("/signup")
	public String getSignup() {
		return "/user/signup";
	}
	
	@PostMapping("/signup")
	public String postSignup(UserVO uvo) {
		int isOk = usv.signUp(uvo);
		return "/user/signupCompl";
	}
}
