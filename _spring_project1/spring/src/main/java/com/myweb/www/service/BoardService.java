package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardService {

	int register(BoardVO bvo);

	List<BoardVO> list(PagingVO pvo);

	BoardVO detail(int bno);

	int modify(BoardVO bvo);

	int remove(int bno);

	int getTotalCount();

}
