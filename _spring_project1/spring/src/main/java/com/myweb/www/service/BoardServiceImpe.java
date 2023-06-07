package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpe implements BoardService {
	@Inject
	private BoardDAO bdao;

	@Override
	public int register(BoardVO bvo) {
		log.info(">>> register service in");
		int isOk = bdao.insertRegi(bvo);
		return isOk;
	}

	@Override
	public List<BoardVO> list(BoardVO bvo) {
		log.info(">>> list service in");
		return bdao.selectList(bvo);
	}

	@Override
	public BoardVO detail(int bno) {
		log.info(">>> detail service in");
		int isOk = bdao.readcount(bno);
		return bdao.selectOne(bno);
	}

	@Override
	public int modify(BoardVO bvo) {
		log.info(">>> modify service in");
		int isOk = bdao.updateMod(bvo);
		return isOk;
	}

	@Override
	public int remove(int bno) {
		log.info(">>> remove service in");
		int isOk = bdao.delete(bno);
		return isOk;
	}
	
	
}