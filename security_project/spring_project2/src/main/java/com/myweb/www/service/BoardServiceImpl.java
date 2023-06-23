package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.BFileVO;
import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.repository.BCommentDAO;
import com.myweb.www.repository.BFileDAO;
import com.myweb.www.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO bdao;
	@Inject
	private BCommentDAO cdao;
	@Inject
	private BFileDAO bfdao;

	@Override
	public int register(BoardVO bvo) {
		// TODO Auto-generated method stub
		return bdao.insertBoard(bvo);
	}

	@Override
	public List<BoardVO> getList() {
		// TODO Auto-generated method stub
		return bdao.listBoard();
	}

	@Transactional
	@Override
	public BoardDTO getDetail(long bno) {
		bdao.updateBoardReadCount(bno, 1);
		return new BoardDTO(bdao.selectOneBoard(bno),bfdao.selectListBFile(bno)) ;
	}

	@Override
	public int modify(BoardVO bvo) {
		// TODO Auto-generated method stub
		return bdao.updateBoard(bvo);
	}

	@Override
	public int delete(long bno) {
		// 댓글 삭제 추가
		cdao.deleteAllBComment(bno);
		return bdao.deleteBoard(bno);
	}

	@Override
	public List<BoardVO> getList(PagingVO pagingVO) {
		// TODO Auto-generated method stub
		return bdao.selectListBoardPaging(pagingVO);
	}

	@Override
	public int getTotalCount(PagingVO pagingVO) {
		// TODO Auto-generated method stub
		return bdao.selectOneTotalCount(pagingVO);
	}

	@Transactional
	@Override
	public int register(BoardDTO bdto) {
		int isUp = bdao.insertBoard(bdto.getBvo());
		
		if (isUp > 0 && bdto.getBfList().size() > 0) {
			long bno = bdao.selectOneBno();
			for (BFileVO bfvo : bdto.getBfList()) {
				bfvo.setBno(bno);
				isUp *= bfdao.insertBFile(bfvo);
			}
		}
		return isUp;
	}

	@Transactional
	@Override
	public int modify(BoardDTO bdto) {
		int isUp = bdao.updateBoard(bdto.getBvo());
		if(bdto.getBfList() != null) {
			if (isUp > 0 && bdto.getBfList().size() > 0) {
				long bno = bdto.getBvo().getBno();
				for (BFileVO bfvo : bdto.getBfList()) {
					bfvo.setBno(bno);
					isUp *= bfdao.insertBFile(bfvo);
				}
			}			
		}
		bdao.updateBoardReadCount(bdto.getBvo().getBno(), -2);
		return isUp;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public int removeFile(String uuid) {
		// 파일 삭제
		long bno = bfdao.selectOneBno(uuid);
		int isDel = bfdao.deleteBFile(uuid);
		int cnt = bfdao.selectOneFileCount(bno);
		bdao.updateBoardFileCount(bno, cnt);
		return isDel;
	}
}
