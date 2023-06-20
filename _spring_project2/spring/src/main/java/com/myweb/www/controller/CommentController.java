package com.myweb.www.controller;

import java.util.List;

import javax.inject.Inject;

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
	
	@PostMapping(value="/post", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> post(@RequestBody CommentVO cvo){
		isOk = csv.post(cvo);
		return isOk>0? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/{bno}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<CommentVO>> getList(@PathVariable("bno")int bno){
		List<CommentVO> list = csv.list(bno);
		return new ResponseEntity<List<CommentVO>>(list, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{cno}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("cno")int cno){
		isOk = csv.remove(cno);
		return isOk>0? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping(value="/modify", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(@RequestBody CommentVO cvo){
		isOk = csv.modify(cvo);
		return isOk>0? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
