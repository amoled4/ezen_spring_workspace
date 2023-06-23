package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardVO;

public interface BoardService {

	int boardRegister(BoardVO bvo);

	List<BoardVO> list(BoardVO bvo);

	BoardVO detail(int bno);

	void modify(BoardVO bvo);

	void remove(BoardVO bvo);

	void readcount(int bno);

}
