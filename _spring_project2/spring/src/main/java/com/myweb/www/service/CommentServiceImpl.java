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
		int isOk = cdao.post(cvo);
		return isOk;
	}

	@Override
	public List<CommentVO> list(int bno) {
		// TODO Auto-generated method stub
		return cdao.list(bno);
	}

	@Override
	public int remove(int cno) {
		int isOk = cdao.delete(cno);
		return isOk;
	}

	@Override
	public int modify(CommentVO cvo) {
		int isOk = cdao.update(cvo);
		return isOk;
	}
}
