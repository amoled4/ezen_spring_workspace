package com.myweb.www.service;

import com.myweb.www.domain.UserVO;

public interface UserService {

	UserVO login(String id, String pw);

	int signUp(UserVO uvo);

}
