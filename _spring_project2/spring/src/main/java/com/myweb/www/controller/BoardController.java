package com.myweb.www.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.FileHandler;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/board/*")
@Slf4j
@Controller
public class BoardController {
	@Inject
	private BoardService bsv;
	@Inject
	private FileHandler fhd;
	
	@GetMapping("/list")
	public String list(PagingVO pvo, Model m) {
		List<BoardVO> list = bsv.list(pvo);
		m.addAttribute("list", list);
		int totalCount = bsv.getTotalCount(pvo);
		PagingHandler ph = new PagingHandler(pvo, totalCount);
		m.addAttribute("ph", ph);
		return "/board/list";
	}
	
	@GetMapping("/register")
	public String getRegister() {
		return "/board/register";
	}
	
	@PostMapping("/register")
	public String postRegister(RedirectAttributes rAttr, BoardVO bvo,@RequestParam(name="files", required = false)MultipartFile[] files) {
		List<FileVO> flist = null;
		if(files[0].getSize()>0) {
			flist = fhd.uploadFiles(files);
		}else {
			log.info("file null");
		}
		BoardDTO bdto = new BoardDTO(bvo, flist);
		int isOk = bsv.register(bdto);
		rAttr.addFlashAttribute("isOk",isOk);
		return "redirect:/board/list";
	}
}
