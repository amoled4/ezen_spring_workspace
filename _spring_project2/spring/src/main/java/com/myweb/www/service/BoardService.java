package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardService {

	List<BoardVO> list(PagingVO pvo);

	int getTotalCount(PagingVO pvo);

	int register(BoardDTO bdto);

}
