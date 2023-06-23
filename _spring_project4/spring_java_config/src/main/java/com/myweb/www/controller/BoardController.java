package com.myweb.www.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@PostMapping("/register")
	public String register(Model m, BoardVO bvo) {
		log.info(">>>> bvo {} "+bvo);
		int isOk = bsv.boardRegister(bvo);
		return "redirect:/board/list";
	}
	
	@GetMapping("/list")
	public String list(BoardVO bvo, Model m) {
		List<BoardVO> list = bsv.list(bvo);
		m.addAttribute("list", list);
		return "/board/list";
	}
	
	@GetMapping({"/detail", "/modify"})
	public void detail(@RequestParam("bno")int bno, HttpServletRequest request, Model m) {
		BoardVO bvo = bsv.detail(bno);
		String mapping = request.getRequestURI();
		String path = mapping.substring(mapping.lastIndexOf("/")+1);
		if(path.equals("detail")) {
			bsv.readcount(bno);
		}
		m.addAttribute("bvo", bvo);
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO bvo) {
		log.info("bvo : "+bvo.toString());
		bsv.modify(bvo);
		return "redirect:/board/list";
	}
	
	@GetMapping("/remove")
	public String remove(BoardVO bvo) {
		bsv.remove(bvo);
		return "redirect:/board/list";
	}
	
}
