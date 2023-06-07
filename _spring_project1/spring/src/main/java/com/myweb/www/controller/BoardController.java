package com.myweb.www.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/board/*")
@Slf4j
@Controller
public class BoardController {
	@Inject
	private BoardService bsv;
	
	// insert, update, delete => redirect 처리
	//RedirectAttributes 객체 사용 : 데이터의 새로고침
	
	@GetMapping("list")
	public String list(Model m, BoardVO bvo) {
		List<BoardVO> list = bsv.list(bvo);
		m.addAttribute("list", list);
		return "/board/list";
	}
	
	@GetMapping("/register")
	public String register() {
		return "/board/register";
	}
	
	@PostMapping("/register")
	public String registerPost(RedirectAttributes rAttr, BoardVO bvo) {
		log.info(">>> bvo "+bvo.toString());
		int isOk = bsv.register(bvo);
		log.info(">> 글 작성 > "+(isOk>0?"성공":"실패"));
		return "redirect:/board/list";
	}
	
	// detail을 가져와야 하는 케이스 : detail, modify
	
	@GetMapping({"/detail","/modify"})
	public void detail(@RequestParam("bno")int bno, Model m) {
		log.info(">>> bno "+bno);
		BoardVO bvo = bsv.detail(bno);
		m.addAttribute("board", bvo);
	}
	
	@PostMapping("/modify")
	public String modifyPost(RedirectAttributes rAttr, BoardVO bvo) {
		int isOk = bsv.modify(bvo);
		log.info(">>> 글 수정 > "+(isOk>0?"성공":"실패"));
		return "redirect:/board/list";
	}
	
	@GetMapping("/remove")
	public String remove(RedirectAttributes rAttr, @RequestParam("bno")int bno) {
		// DB상 update하기 isDel = "y" => 삭제한 글 처리
		int isOk = bsv.remove(bno);
		return "redirect:/board/list";
	}
}