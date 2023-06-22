package com.myweb.www.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/board/*")
@Controller
public class BoardController {
	@Inject
	private BoardService bsv;
	
	@GetMapping("/register")
	public void register() {}
	
	public String register(Model m, BoardVO bvo) {
		log.info(">>>> bvo {} "+bvo);
		int isOk = bsv.boardRegister(bvo);
		return "/";
	}
}
