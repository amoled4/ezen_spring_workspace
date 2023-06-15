package com.myweb.www.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.myweb.www.domain.UserVO;
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
	
	// insert, update, delete => redirect 처리
	//RedirectAttributes 객체 사용 : 데이터의 새로고침
	
	@GetMapping("/list")
	public String list(Model m, PagingVO pvo) {
//		log.info(">>> pageno : "+pvo.getPageNo());
		log.info(pvo.toString());
		List<BoardVO> list = bsv.list(pvo);
		m.addAttribute("list", list);
		int totalCount = bsv.getTotalCount(pvo);
		PagingHandler ph = new PagingHandler(pvo, totalCount);
		m.addAttribute("ph", ph);
		return "/board/list";
	}
	
	@GetMapping("/register")
	public String register() {
		return "/board/register";
	}
	
	// required(필수 여부) : false인 경우 해당 파라미터가 없더라도 예외가 발생하지 않음
	@PostMapping("/register")
	public String registerPost(RedirectAttributes rAttr, BoardVO bvo, @RequestParam(name="files", required = false)MultipartFile[] files) {
		log.info(">>> bvo "+bvo.toString());
		log.info("files : "+files.toString());
		List<FileVO> flist = null;
		// file 처리 => Handler로 처리  //multiple이라 배열
		if(files[0].getSize()>0) {   // 데이터가 있다라는 것을 의미
			// 파일 배열을 경로설정, fvo set 해서 리스트로 리턴
			flist = fhd.uploadFiles(files);   // fvo의 리스트를 넣어줌
		}else {
			log.info("file null");
		}
		// 파일과 board 처리를 별도로 할 것인지 같이 묶어서 처리를 할 것인지 결정 -> 일반적으로 묶어서 처리
		BoardDTO bdto = new BoardDTO(bvo, flist);
		int isOk = bsv.register(bdto);
		// int isOk = bsv.register(bvo);
		log.info(">> 글 작성 > "+(isOk>0?"성공":"실패"));
		rAttr.addFlashAttribute("isOk",isOk);
		return "redirect:/board/list";
	}
	
	// detail을 가져와야 하는 케이스 : detail, modify
	@GetMapping({"/detail","/modify"})
	public void detail(@RequestParam("bno")int bno, Model m, HttpServletRequest request) {
		log.info(">>> bno "+bno);
		
		String mapping = request.getRequestURI();
		String path = mapping.substring(mapping.lastIndexOf("/")+1);
		if(path.equals("detail")) {
			int isOk = bsv.readcount(bno);
		}
//		BoardVO bvo = bsv.detail(bno);
		BoardDTO bdto = bsv.detailFile(bno);
		m.addAttribute("boardDTO", bdto);
	}
	
	@PostMapping("/modify")
	public String modifyPost(RedirectAttributes rAttr, BoardVO bvo, HttpServletRequest request, @RequestParam(name="files", required = false)MultipartFile[] files) {
		// 세션의 로그인 id가 작성자와 일치하면 삭제 아니면 스크립트에서 작성자가 일치하지 않습니다
//		HttpSession ses = request.getSession();
//		UserVO sesUser = (UserVO)ses.getAttribute("ses");   // 세션 객체
//		UserVO user = udao.getUser(sesUser.getId());
		
		List<FileVO> flist = null;
		if(files[0].getSize()>0) {
			flist = fhd.uploadFiles(files);
		}
		BoardDTO bdto = new BoardDTO(bvo, flist);
		int isOk = bsv.modifyFile(bdto);
//		int isOk = bsv.modify(bvo);
		log.info(">>> 글 수정 > "+(isOk>0?"성공":"실패"));
		
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
