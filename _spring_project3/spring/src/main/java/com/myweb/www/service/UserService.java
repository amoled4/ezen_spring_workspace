package com.myweb.www.service;

import com.myweb.www.domain.UserVO;

public interface UserService {

	UserVO login(UserVO uvo);

	int signup(UserVO uvo);

}
