package com.myweb.www.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/comment/*")
@Slf4j
@Controller
public class CommentController {
	@Inject
	private CommentService csv;
	private int isOk;
	
	// ResponseEntity
	// value : mapping 값 설정, consumes : 가져오는 값의 형태, produces : 보낼 때의 형식
	@PostMapping(value="/post", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> post(@RequestBody CommentVO cvo){
		log.info(">>>cvo : "+cvo);
		// DB 연결
		isOk = csv.post(cvo);
		log.info(">>> 댓글 등록 > "+(isOk>0?"성공":"실패"));
		
		// 리턴을 위해서는 response의 통신상태를 같이 리턴해야 함
		return isOk>0? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
