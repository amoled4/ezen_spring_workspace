package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.repository.CommentDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {
	@Inject
	private CommentDAO cdao;

	@Override
	public int post(CommentVO cvo) {
		log.info(">>> post service in");
		int isOk = cdao.post(cvo);
		return isOk;
	}

	@Override
	public List<CommentVO> list(int bno) {
		log.info(">>> list service in");
		return cdao.list(bno);
	}

	@Override
	public int remove(int cno) {
		log.info(">>> remove service in");
		int isOk = cdao.delete(cno);
		return isOk;
	}

	@Override
	public int modify(CommentVO cvo) {
		log.info(">>> update service in");
		int isOk = cdao.update(cvo);
		return isOk;
	}
}
