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
import com.Kcompany.Kboard.service.FBoardService;
import com.Kcompany.Kboard.service.FReplyService;
import com.Kcompany.Kboard.vo.FBoardVO;
import com.Kcompany.Kboard.vo.MemberVO;
import com.Kcompany.Kboard.vo.FReplyVO;

@Controller
public class FreeBoardReplyController {	
	
	@Autowired
	private FReplyService r_service;
	
	@Autowired
	private FBoardService b_service;
	
	@Autowired
	private ReplyPaging r_paging;
	
	
	// 댓글 작성하기
	@RequestMapping("/replyWrite")
	public ModelAndView replyWrite(FReplyVO reply, HttpServletRequest request, ReplyPageCriteria pc) {
		ModelAndView mav = new ModelAndView();

		// 현재 로그인 중인 아이디의 정보를 service의 write함수에 실어 보낸다.
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO)session.getAttribute("member");
		String sessionId = member.getMemId();
		
		r_service.write(reply, sessionId);
		FBoardVO boardVO = b_service.view(reply.getB_index());
		
		// 댓글을 리스트의 형태로 보여준다.
		List<FReplyVO> list = r_service.listAll(boardVO.getB_index(), pc);
		int listCnt = list.size();
		
		// 댓글 페이징 처리
		r_paging.setPage(pc);
		int totalPageNum = r_service.totalCount(boardVO.getB_index());
		r_paging.setTotalCount(totalPageNum);
		
		// Request영역에 Attribute저장
		mav.addObject("view", boardVO);
		mav.addObject("viewReply", list);
		mav.addObject("viewReplyCnt", listCnt);
		mav.addObject("paging",r_paging);
		mav.setViewName("/board/freeBoard/view");
		return mav;
	}
	
	// 댓글 수정하기
	@RequestMapping("/replyCorrect")
	public ModelAndView replyCorrect(FReplyVO reply, HttpServletRequest request, ReplyPageCriteria pc) {
		ModelAndView mav = new ModelAndView();
		
		// 현재 로그인 중인 아이디의 정보를 service의 correct함수에 실어 보낸다.
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO)session.getAttribute("member");
		String memId = member.getMemId();
		
		r_service.correct(reply, memId);
		FBoardVO boardVO = b_service.view(reply.getB_index());
		
		// 댓글을 리스트의 형태로 보여준다.
		List<FReplyVO> list = r_service.listAll(boardVO.getB_index(), pc);
		int listCnt = list.size();
		
		// 댓글 페이징 처리
		r_paging.setPage(pc);
		int totalPageNum = r_service.totalCount(boardVO.getB_index());
		r_paging.setTotalCount(totalPageNum);
		
		// Request영역에 Attribute저장
		mav.addObject("view", boardVO);
		mav.addObject("viewReply", list);
		mav.addObject("viewReplyCnt", listCnt);
		mav.addObject("paging",r_paging);
		mav.setViewName("/board/freeBoard/view");
		return mav;
	}
	
	// 댓글 삭제하기
	@RequestMapping("/replyDelete")
	public ModelAndView replyDelete(@RequestParam("r_index") int r_index, @RequestParam("b_index") int b_index, HttpServletRequest request, ReplyPageCriteria pc) {
		ModelAndView mav = new ModelAndView();
		
		// 현재 로그인 중인 아이디의 정보를 service의  delete함수에 실어 보낸다.
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO)session.getAttribute("member");
		String memId = member.getMemId();
		
		r_service.delete(r_index, memId);
		FBoardVO boardVO = b_service.view(b_index);
		
		// 댓글을 리스트의 형태로 보여준다.
		List<FReplyVO> list = r_service.listAll(boardVO.getB_index(), pc);
		int listCnt = list.size();
		
		// Request영역에 Attribute저장
		mav.addObject("view", boardVO);
		mav.addObject("viewReply", list);
		mav.addObject("viewReplyCnt", listCnt);
		mav.setViewName("/board/freeBoard/view");		
		return mav;
	}
	
}









