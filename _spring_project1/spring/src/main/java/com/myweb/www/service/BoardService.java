package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardVO;

public interface BoardService {

	int register(BoardVO bvo);

	List<BoardVO> list(BoardVO bvo);

	BoardVO detail(int bno);

	int modify(BoardVO bvo);

	int remove(int bno);

}
