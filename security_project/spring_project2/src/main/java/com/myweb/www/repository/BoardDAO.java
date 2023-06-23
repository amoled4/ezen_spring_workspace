package com.myweb.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardDAO {

	int insertBoard(BoardVO bvo);

	List<BoardVO> listBoard();

	BoardVO detailBoard(long bno);

	int updateBoard(BoardVO bvo);

	int deleteBoard(long bno);

	List<BoardVO> selectListBoardPaging(PagingVO pagingVO);

	int selectOneTotalCount(PagingVO pagingVO);

	long selectOneBno();

	BoardVO selectOneBoard(long bno);

	int updateBoardFileCount(@Param("bno") long bno, @Param("cnt") int cnt);
	
	void updateBoardReadCount(@Param("bno")long bno, @Param("cnt")int cnt);
	
	void updateBoardCmtQty(@Param("bno")long bno, @Param("cnt")int cnt);

}
