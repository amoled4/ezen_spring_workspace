package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.BoardVO;

public interface BoardDAO {

	int insertBoard(BoardVO bvo);

	List<BoardVO> list(BoardVO bvo);

	BoardVO detail(int bno);

	void updateMod(BoardVO bvo);

	void deleteBoard(BoardVO bvo);

	void readcount(int bno);

}
