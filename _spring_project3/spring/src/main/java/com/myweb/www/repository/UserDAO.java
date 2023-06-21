package com.myweb.www.repository;

import com.myweb.www.domain.UserVO;

public interface UserDAO {

	UserVO getUser(UserVO uvo);

	int signup(UserVO uvo);

}
