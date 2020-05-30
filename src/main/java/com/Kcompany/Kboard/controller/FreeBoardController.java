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
import com.Kcompany.Kboard.service.FBoardService;
import com.Kcompany.Kboard.service.FReplyService;
import com.Kcompany.Kboard.vo.FBoardVO;
import com.Kcompany.Kboard.vo.MemberVO;
import com.Kcompany.Kboard.vo.FReplyVO;

@Controller
public class FreeBoardController {

	@Autowired
	private FBoardService b_service;
	
	@Autowired
	private FReplyService r_service;
	
	@Autowired
	private BoardPaging b_paging;
	
	@Autowired
	private ReplyPaging r_paging;

	
	// 게시글 목록 열기(로그인 후 메인화면)
	@RequestMapping("/openList")
	public ModelAndView openList(BoardPageCriteria pc) {
		ModelAndView mav = new ModelAndView();
		List<FBoardVO> list = b_service.listAll(pc);
		
		// 게시글 페이징 처리
		b_paging.setPage(pc);
		int totalPageNum = b_service.totalCount();
		b_paging.setTotalCount(totalPageNum);
		
		// Request영역에 Attribute저장
		mav.addObject("list", list);
		mav.addObject("paging", b_paging);
		mav.setViewName("/board/freeBoard/list");
		return mav;
	}
	
	// 글 목록에서 게시글 선택	
	@RequestMapping(value="/openContent", method = RequestMethod.GET)
	public ModelAndView openContent(@RequestParam("b_index")int index, ReplyPageCriteria pc) {
		ModelAndView mav = new ModelAndView();
		FBoardVO boardVO = b_service.view(index);
				
		// 해당 게시글의 index를 이용해서 댓글을 리스트 형태로 보여준다.
		List<FReplyVO> list = r_service.listAll(boardVO.getB_index(), pc);
		int listCnt = list.size();
		
		// 댓글 페이징처리
		r_paging.setPage(pc);
		int totalPageNum = r_service.totalCount(index);
		r_paging.setTotalCount(totalPageNum);
		
		// Request영역에 Attribute저장
		mav.addObject("view", boardVO);
		mav.addObject("viewReply", list);
		mav.addObject("viewReplyCnt", listCnt);
		mav.addObject("paging", r_paging);
		mav.setViewName("/board/freeBoard/view");
		return mav;
	}
	
	// 게시글 추천하기
	@RequestMapping("/recommend")
	public ModelAndView recommend(@RequestParam("b_index")int index, ReplyPageCriteria pc) {
		ModelAndView mav = new ModelAndView();
		b_service.recommend(index);
		FBoardVO boardvo = b_service.simpleRead(index);
		
		// 댓글을 리스트로 형태로 보여준다.
		List<FReplyVO> list = r_service.listAll(boardvo.getB_index(),pc);
		int listCnt = list.size();
		
		// Request영역에 Attribute저장
		mav.addObject("view", boardvo);
		mav.addObject("viewReply", list);
		mav.addObject("viewReplyCnt", listCnt);
		mav.setViewName("/board/freeBoard/view");
		return mav;
	}
	
	// 게시글 작성하기 폼
	@RequestMapping("/writeForm")
	public String writeForm() {
		return "/board/freeBoard/write";
	}
	// 게시글 작성하기
	@RequestMapping("/writeBoard")
	public ModelAndView writeBoard(FBoardVO board) {
		ModelAndView mav = new ModelAndView();
		FBoardVO result = b_service.write(board);
		FBoardVO boardVO = b_service.view(result.getB_index());
		
		// Request영역에 Attribute저장
		mav.addObject("view", boardVO);
		mav.setViewName("/board/freeBoard/view");
		return mav;
	}
	
	// 게시글 수정하기 폼
	@RequestMapping("/correctBoardForm")
	public String correctBoardForm(Model model, @RequestParam("b_index") int index, HttpServletRequest request) {
		
		// correctView로 현재글의 내용을 수정 페이지와 함께 보여줌.
		FBoardVO boardVO = b_service.simpleRead(index);
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		String sessionId = memberVO.getMemId();
		
		// 글 작성자와 현재 로그인된 사용자의 아이디가 다르면 수정할 수 없다.
		if(boardVO.getB_memId().equals(sessionId)) {
			model.addAttribute("correctView", boardVO);
			return "/board/freeBoard/correct";
		}else {
			System.out.println("Error!!!!!!!!!!!!!!!!!!!");
			return "redirect:/board/freeBoard/correctError";
		}
	}
	// 게시글 수정하기
	@RequestMapping("/correctBoard")
	public ModelAndView correctBoard(FBoardVO board, HttpServletRequest request, ReplyPageCriteria pc) {
		ModelAndView mav = new ModelAndView();
		 b_service.correct(board);
		FBoardVO boardVO = b_service.view(board.getB_index());
		
		// 댓글을 리스트로 형태로 보여준다.
		List<FReplyVO> list = r_service.listAll(boardVO.getB_index(), pc);
		int listCnt = list.size();
		
		// Request영역에 Attribute저장
		mav.addObject("view", boardVO);
		mav.addObject("viewReply", list);
		mav.addObject("viewReplyCnt", listCnt);
		mav.setViewName("/board/freeBoard/view");
		
		return mav;
	}
	
	// 게시글 삭제하기		
	@RequestMapping("deleteBoard")
	public String deleteBoard(@RequestParam("b_index") int b_index) {
		b_service.delete(b_index);
		
		return "redirect:/openList";
	}

}















