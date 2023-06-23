package com.myweb.www.controller;

import javax.inject.Inject;
import javax.print.attribute.standard.Media;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myweb.www.domain.BCommentVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.service.BCommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/bcomment/*")
@Controller
public class BCommentController {
	
	@Inject
	private BCommentService csv;
	
	@PostMapping(value = "/post", consumes = "application/json", 
			produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> post(@RequestBody BCommentVO cvo){
		log.debug(">>>> cvo" + cvo);
		return csv.register(cvo) > 0 ?
				new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@GetMapping(value="/{bno}/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PagingHandler> spread(@PathVariable("bno") long bno , 
			@PathVariable("page") int page){
		log.debug(">>> bcooment bno :{}/{}", bno, page);
		PagingVO pgvo = new PagingVO(page, 10);
		//PagingHandler ph = csv.getList(bno, pgvo);
		
		return new ResponseEntity<PagingHandler>(csv.getList(bno, pgvo),
									HttpStatus.OK);
	}
	
	@PutMapping(value="/{cno}", consumes = "application/json", 
			produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> edit(@PathVariable("cno") long cno, 
			@RequestBody BCommentVO cvo){
		return csv.modify(cvo) > 0 ? 
				new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping(value="/{cno}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> delete(@PathVariable("cno") long cno){
		return csv.remove(cno) > 0 ?
				new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
