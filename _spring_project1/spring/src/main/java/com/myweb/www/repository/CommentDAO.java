package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.CommentVO;

public interface CommentDAO {

	int post(CommentVO cvo);

	List<CommentVO> list(int bno);

	int delete(int cno);

	int update(CommentVO cvo);

}
