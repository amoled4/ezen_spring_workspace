package com.myweb.www.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping({"/detail","/modify"})
	public void detail(@RequestParam("bno")int bno, Model m, HttpServletRequest request) {
		log.info(">>> bno "+bno);
		
		String mapping = request.getRequestURI();
		String path = mapping.substring(mapping.lastIndexOf("/")+1);
		if(path.equals("detail")) {
			int isOk = bsv.readcount(bno);
		}
		BoardDTO bdto = bsv.detailFile(bno);
		m.addAttribute("boardDTO", bdto);
	}
	
	@PostMapping("/modify")
	public String modifyPost(RedirectAttributes rAttr, BoardVO bvo, HttpServletRequest request, @RequestParam(name="files", required = false)MultipartFile[] files) {
		
		List<FileVO> flist = null;
		if(files[0].getSize()>0) {
			flist = fhd.uploadFiles(files);
		}
		BoardDTO bdto = new BoardDTO(bvo, flist);
		int isOk = bsv.modifyFile(bdto);
		return "redirect:/board/list";
	}
	
	@GetMapping("/remove")
	public String remove(RedirectAttributes rAttr, @RequestParam("bno")int bno) {
		// DB상 update하기 isDel = "y" => 삭제한 글 처리
		int isOk = bsv.remove(bno);
		return "redirect:/board/list";
	}
	
	@DeleteMapping(value="/file/{uuid}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> removeFile(@PathVariable("uuid")String uuid){
		log.info(">>> uuid : "+uuid);
		return bsv.removeFile(uuid) > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
