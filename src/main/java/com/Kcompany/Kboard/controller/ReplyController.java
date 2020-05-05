package com.Kcompany.Kboard.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.Kcompany.Kboard.common.paging.ReplyPageCriteria;
import com.Kcompany.Kboard.common.paging.ReplyPaging;
import com.Kcompany.Kboard.service.BoardService;
import com.Kcompany.Kboard.service.ReplyService;
import com.Kcompany.Kboard.vo.BoardVO;
import com.Kcompany.Kboard.vo.MemberVO;
import com.Kcompany.Kboard.vo.ReplyVO;

@Controller
public class ReplyController {

	// openContent 하면, 댓글 보여주기 -> 이거는 BoardController에서 가공하자
	
	
	@Autowired
	ReplyService r_service;
	
	@Autowired
	BoardService b_service;
	
	
	// 댓글 쓰기
	
	@RequestMapping("/replyWrite")
	public ModelAndView replyWrite(ReplyVO reply, HttpServletRequest request, ReplyPageCriteria pc) {

		ModelAndView mav = new ModelAndView();
		// index는 현 페이지의 index이다.
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO)session.getAttribute("member");
		String memId = member.getMemId();
		
		r_service.write(reply, memId);

		//이거는 board의 view이다.
		BoardVO boardVO = b_service.view(reply.getB_index());
		
		List<ReplyVO> list = r_service.listAll(boardVO.getB_index(), pc);
		int listCnt = list.size();
		
		ReplyPaging paging = new ReplyPaging();
		paging.setPaging(pc);
		int totalPageNum = r_service.totalCount(boardVO.getB_index());
		paging.setTotalCount(totalPageNum);
		
		mav.addObject("view", boardVO);
		mav.addObject("viewReply", list);
		mav.addObject("viewReplyCnt", listCnt);
		mav.addObject("paging",paging);
		mav.setViewName("/board/view");
		
		return mav;
	}
	
	
	
	// 댓글 수정하기
	@RequestMapping("/replyCorrect")
	public ModelAndView replyCorrect(ReplyVO reply, HttpServletRequest request, ReplyPageCriteria pc) {
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO)session.getAttribute("member");
		String memId = member.getMemId();
		
		r_service.correct(reply, memId);
		
		BoardVO boardVO = b_service.view(reply.getB_index());
		
		List<ReplyVO> list = r_service.listAll(boardVO.getB_index(), pc);
		int listCnt = list.size();
		
		ReplyPaging paging = new ReplyPaging();
		paging.setPaging(pc);
		int totalPageNum = r_service.totalCount(boardVO.getB_index());
		paging.setTotalCount(totalPageNum);
		
		mav.addObject("view", boardVO);
		mav.addObject("viewReply", list);
		mav.addObject("viewReplyCnt", listCnt);
		mav.addObject("paging",paging);
		mav.setViewName("/board/view");
		
		return mav;
	}
	
	
	
	// 댓글 삭제하기
	@RequestMapping("/replyDelete")
	public ModelAndView replyDelete(@RequestParam("r_index") int r_index, @RequestParam("b_index") int b_index, HttpServletRequest request, ReplyPageCriteria pc) {
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO)session.getAttribute("member");
		String memId = member.getMemId();
		
		r_service.delete(r_index, memId);
		
		BoardVO boardVO = b_service.view(b_index);
		
		List<ReplyVO> list = r_service.listAll(boardVO.getB_index(), pc);
		int listCnt = list.size();
		
		mav.addObject("view", boardVO);
		mav.addObject("viewReply", list);
		mav.addObject("viewReplyCnt", listCnt);
		mav.setViewName("/board/view");
		
		return mav;
	}
	
}









