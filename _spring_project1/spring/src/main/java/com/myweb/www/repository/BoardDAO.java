package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.BoardVO;

public interface BoardDAO {

	int insertRegi(BoardVO bvo);

	List<BoardVO> selectList(BoardVO bvo);

	BoardVO selectOne(int bno);

	int updateMod(BoardVO bvo);

	int delete(int bno);

	int readcount(int bno);

}