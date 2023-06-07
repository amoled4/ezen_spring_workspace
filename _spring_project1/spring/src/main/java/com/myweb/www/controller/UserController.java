package com.myweb.www.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	// @Slf4j와 같은 의미
	// private static final Logger log = LoggerFactory.getLogger(UserController.class);  
	
	@Inject    // serviceImpl과 연결
	private UserService usv;
	
	@GetMapping("/signup")
	public String index(Model m) {
		log.info("home 접근 완료");
//		m.addAttribute("msg_home", 1);
		return "/user/signup";
	}
	
	@PostMapping("/signup")
	public String signupPost(Model m, UserVO uvo) {
		log.info("회원가입 접근 완료");
		log.info(uvo.toString());
		int isOk = usv.signUp(uvo);
		if(isOk>0) {
			m.addAttribute("msg_signup", 1);
		}
		return "home";   //  결과 페이지
	}
	
	@GetMapping("/login")
	public String loginGet() {
		return "/user/login";
	}
	
	@PostMapping("/login")
	public String loginPost(Model m, String id, String pw, HttpServletRequest request) {
		// 파라미터로 받은 id, pw를 DB에 넘겨 일치하는 객체를 받음
		// passwordencoder.matches(원래 비번, 암호화된 비번) : 매치가 된다면 true
		 UserVO login = usv.login(id, pw);
		// DB에서 얻은 객체가 null이 아니라면 세션 연결해서 저장
		if(login != null) {
			HttpSession ses = request.getSession();
			ses.setAttribute("ses", login);  // 세션에 객체 담기
			ses.setMaxInactiveInterval(60*10);   // 로그인 유지시간(초단위)
			m.addAttribute("login", login);
//			m.addAttribute("msg_login", 1);
		}else {
			m.addAttribute("msg_login", 0);
		}
		return "home";
	}
	
	@GetMapping("/logout")
	public String logout(Model m, HttpServletRequest request) {
//		HttpSession ses = request.getSession();
//		ses.invalidate();
		request.getSession().removeAttribute("ses");
		request.getSession().invalidate();  // 세션끊기
		m.addAttribute("msg_logout",0);
		return "home";
	}
	
}
