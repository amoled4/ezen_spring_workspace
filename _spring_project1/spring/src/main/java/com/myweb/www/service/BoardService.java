package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardService {

	int register(BoardVO bvo);

	List<BoardVO> list(PagingVO pvo);

	BoardVO detail(int bno);

	int modify(BoardVO bvo);

	int remove(int bno);

	int getTotalCount(PagingVO pvo);

	// bvo, fList 묶어서 처리되는 case
	int register(BoardDTO bdto);

	BoardDTO detailFile(int bno);

}
