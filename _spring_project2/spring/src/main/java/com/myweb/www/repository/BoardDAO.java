package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardDAO {

	List<BoardVO> selectListBoard(PagingVO pvo);

	int getTotalCount(PagingVO pvo);

	int insertRegi(BoardVO bvo);

	int selectBno();

	int readcount(int bno);

	BoardVO selectOne(int bno);

	int updateMod(BoardVO bvo);

	int delete(int bno);

}
