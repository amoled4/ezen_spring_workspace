package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.repository.BoardDAO;
import com.myweb.www.repository.FileDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {
	@Inject
	private BoardDAO bdao;
	
	private FileDAO fdao;

	@Override
	public List<BoardVO> list(PagingVO pvo) {
		return bdao.selectListBoard(pvo);
	}

	@Override
	public int getTotalCount(PagingVO pvo) {
		// TODO Auto-generated method stub
		return bdao.getTotalCount(pvo);
	}

	@Override
	public int register(BoardDTO bdto) {
		int isOk = bdao.insertRegi(bdto.getBvo());
		if(bdto.getFlist() == null) { 
			isOk*=1;  
		}else {
			if(isOk>0 && bdto.getFlist().size()>0) {
				int bno = bdao.selectBno();
				for(FileVO fvo : bdto.getFlist()) {
					fvo.setBno(bno);
					log.info(">>>> insert File : "+fvo.toString());
					isOk *= fdao.insertFile(fvo);
				}
			}
		}
		return isOk;
	}
}
