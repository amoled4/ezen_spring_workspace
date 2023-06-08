package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardDAO {

	int insertRegi(BoardVO bvo);

	List<BoardVO> selectList(PagingVO pvo);

	BoardVO selectOne(int bno);

	int updateMod(BoardVO bvo);

	int delete(int bno);

	int readcount(int bno);

	int getTotalCount();

	List<BoardVO> selectBoardListPaging(PagingVO pvo);

}
