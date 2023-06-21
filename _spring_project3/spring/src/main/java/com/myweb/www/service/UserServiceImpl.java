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
	public UserVO login(UserVO uvo) {
		UserVO login = udao.getUser(uvo);
		if(login == null) {
			
			return null;
		}
		if(passwordencoder.matches(uvo.getPw(), login.getPw())) {
			return login;
		}else {
			return null;
		}
	}

	@Override
	public int signup(UserVO uvo) {
		int isOk = udao.signup(uvo);
		return isOk;
	}
	
}
