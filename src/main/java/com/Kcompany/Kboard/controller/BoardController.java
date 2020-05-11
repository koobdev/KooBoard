package com.Kcompany.Kboard.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.Kcompany.Kboard.common.paging.BoardPageCriteria;
import com.Kcompany.Kboard.common.paging.BoardPaging;
import com.Kcompany.Kboard.common.paging.ReplyPageCriteria;
import com.Kcompany.Kboard.common.paging.ReplyPaging;
import com.Kcompany.Kboard.service.BoardService;
import com.Kcompany.Kboard.service.ReplyService;
import com.Kcompany.Kboard.vo.BoardVO;
import com.Kcompany.Kboard.vo.MemberVO;
import com.Kcompany.Kboard.vo.ReplyVO;

@Controller
public class BoardController {

	@Autowired
	BoardService b_service;
	
	@Autowired
	ReplyService r_service;

	
	// 글 목록 열기
	@RequestMapping("/openList")
	public ModelAndView openList(BoardPageCriteria pc) {
		
		ModelAndView mav = new ModelAndView();
		List<BoardVO> list = b_service.listAll(pc);
		
		
		BoardPaging paging = new BoardPaging();
		paging.setPage(pc);
		int totalPageNum = b_service.totalCount();
		paging.setTotalCount(totalPageNum);
		
		
		mav.addObject("list", list);
		mav.addObject("paging", paging);
		mav.setViewName("/board/list");
	
		
		return mav;
	}
	
	// 글 목록에서 게시글 선택	
	@RequestMapping(value="/openContent", method = RequestMethod.GET)
	public ModelAndView openContent(@RequestParam("b_index")int index, ReplyPageCriteria pc) {
		
		ModelAndView mav = new ModelAndView();
		BoardVO boardVO = b_service.view(index);
				
		// 해당 게시글의 index를 이용해서 댓글을 리스트 형태로 보여준다.
		List<ReplyVO> list = r_service.listAll(boardVO.getB_index(), pc);
		int listCnt = list.size();
		
		// 댓글 페이징처리
		ReplyPaging paging = new ReplyPaging();
		paging.setPaging(pc);
		int totalPageNum = r_service.totalCount(index);
		paging.setTotalCount(totalPageNum);
		
		mav.addObject("view", boardVO);
		mav.addObject("viewReply", list);
		mav.addObject("viewReplyCnt", listCnt);
		mav.addObject("paging", paging);
		mav.setViewName("/board/view");
		
		return mav;
	}
	
	// 게시글 추천하기
	@RequestMapping("/recommand")
	public ModelAndView recommand(@RequestParam("b_index")int index, ReplyPageCriteria pc) {
		
		ModelAndView mav = new ModelAndView();
		b_service.recommand(index);
		BoardVO boardvo = b_service.simpleRead(index);
		
		List<ReplyVO> list = r_service.listAll(boardvo.getB_index(),pc);
		int listCnt = list.size();
		
		mav.addObject("view", boardvo);
		mav.addObject("viewReply", list);
		mav.addObject("viewReplyCnt", listCnt);
		mav.setViewName("/board/view");
		
		return mav;
	}
	
	// 게시글 작성하기
	@RequestMapping("/writeForm")
	public String writeForm() {
		return "/board/write";
	}
	
	@RequestMapping("/writeBoard")
	public ModelAndView writeBoard(BoardVO board) {
		
		ModelAndView mav = new ModelAndView();
		
		BoardVO result = b_service.write(board);
		BoardVO boardVO = b_service.view(result.getB_index());
		
		mav.addObject("view", boardVO);
		mav.setViewName("/board/view");
		
		return mav;
	}
	
	
	// 게시글 수정하기
	@RequestMapping("/correctBoardForm")
	public String correctBoardForm(Model model, @RequestParam("b_index") int index, HttpServletRequest request) {
		
		// correctView로 현재글의 내용을 수정 페이지와 함께 보여줌.
		BoardVO boardVO = b_service.simpleRead(index);
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		String sessionId = memberVO.getMemId();
		
		// 글 작성자와 현재 로그인된 사용자의 아이디가 다르면 수정할 수 없다.
		if(boardVO.getB_memId().equals(sessionId)) {
			model.addAttribute("correctView", boardVO);
			return "/board/correct";
		}else {
			System.out.println("Error!!!!!!!!!!!!!!!!!!!");
			return "redirect:/board/correctError";
		}
		
	}
	
	@RequestMapping("/correctBoard")
	public ModelAndView correctBoard(BoardVO board, HttpServletRequest request, ReplyPageCriteria pc) {
		
		ModelAndView mav = new ModelAndView();
		int result = b_service.correct(board);
		if(result != 1) System.out.println("correct Failed");
		
		BoardVO boardVO = b_service.view(board.getB_index());
				
		List<ReplyVO> list = r_service.listAll(boardVO.getB_index(), pc);
		int listCnt = list.size();
		
		mav.addObject("view", boardVO);
		mav.addObject("viewReply", list);
		mav.addObject("viewReplyCnt", listCnt);
		mav.setViewName("/board/view");
		
		return mav;
	}
	
	
	// 게시글 삭제하기		
	@RequestMapping("deleteBoard")
	public String deleteBoard(@RequestParam("b_index") int b_index) {
		
		b_service.delete(b_index);
		
		return "redirect:/openList";
	}

	

}















