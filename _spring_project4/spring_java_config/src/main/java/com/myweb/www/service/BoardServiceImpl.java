package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {
	@Inject
	private BoardDAO bdao;

	@Override
	public int boardRegister(BoardVO bvo) {
		int isOk = bdao.insertBoard(bvo);
		return isOk;
	}

	@Override
	public List<BoardVO> list(BoardVO bvo) {
		List<BoardVO> list = bdao.list(bvo);
		return list;
	}

	@Override
	public BoardVO detail(int bno) {
		
		return bdao.detail(bno);
	}

	@Override
	public void modify(BoardVO bvo) {
		bdao.updateMod(bvo);
		
	}

	@Override
	public void remove(BoardVO bvo) {
		bdao.deleteBoard(bvo);
		
	}

	@Override
	public void readcount(int bno) {
		bdao.readcount(bno);
		
	}


}
