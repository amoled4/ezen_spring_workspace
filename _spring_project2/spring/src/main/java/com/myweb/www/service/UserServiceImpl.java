package com.myweb.www.service;

import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.myweb.www.domain.UserVO;
import com.myweb.www.repository.UserDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	@Inject
	private UserDAO udao;
	@Inject
	BCryptPasswordEncoder passwordencoder;
	@Override
	public UserVO login(String id, String pw) {
		UserVO login = udao.getUser(id);
		if(login == null) {
			return null;
		}
		if(passwordencoder.matches(pw, login.getPw())) {
			return login;
		}else {
			return null;
		}
	}
	@Override
	public int signUp(UserVO uvo) {
		UserVO tempUser = udao.getUser(uvo.getId());
		if(tempUser != null) {
			return 0;
		}
		if(uvo.getId() == null || uvo.getId().length() == 0) {
			return 0;
		}
		if(uvo.getPw() == null || uvo.getPw().length() == 0) {
			return 0;
		}
		
		String pw = uvo.getPw();
		String encodePw = passwordencoder.encode(pw);
		uvo.setPw(encodePw);
		int isOk = udao.inserUser(uvo);
		return isOk;
	}
}
