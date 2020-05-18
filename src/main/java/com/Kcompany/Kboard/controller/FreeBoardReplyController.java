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
	
	
	// ��� ����
	
	@RequestMapping("/replyWrite")
	public ModelAndView replyWrite(FReplyVO reply, HttpServletRequest request, ReplyPageCriteria pc) {

		ModelAndView mav = new ModelAndView();
		// index�� �� �������� index�̴�.
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO)session.getAttribute("member");
		String sessionId = member.getMemId();
		
		r_service.write(reply, sessionId);

		//�̰Ŵ� board�� view�̴�.
		FBoardVO boardVO = b_service.view(reply.getB_index());
		
		List<FReplyVO> list = r_service.listAll(boardVO.getB_index(), pc);
		int listCnt = list.size();
		
		ReplyPaging paging = new ReplyPaging();
		paging.setPaging(pc);
		int totalPageNum = r_service.totalCount(boardVO.getB_index());
		paging.setTotalCount(totalPageNum);
		
		mav.addObject("view", boardVO);
		mav.addObject("viewReply", list);
		mav.addObject("viewReplyCnt", listCnt);
		mav.addObject("paging",paging);
		mav.setViewName("/board/freeBoard/view");
		
		return mav;
	}
	
	
	
	// ��� �����ϱ�
	@RequestMapping("/replyCorrect")
	public ModelAndView replyCorrect(FReplyVO reply, HttpServletRequest request, ReplyPageCriteria pc) {
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO)session.getAttribute("member");
		String memId = member.getMemId();
		
		r_service.correct(reply, memId);
		
		FBoardVO boardVO = b_service.view(reply.getB_index());
		
		List<FReplyVO> list = r_service.listAll(boardVO.getB_index(), pc);
		int listCnt = list.size();
		
		ReplyPaging paging = new ReplyPaging();
		paging.setPaging(pc);
		int totalPageNum = r_service.totalCount(boardVO.getB_index());
		paging.setTotalCount(totalPageNum);
		
		mav.addObject("view", boardVO);
		mav.addObject("viewReply", list);
		mav.addObject("viewReplyCnt", listCnt);
		mav.addObject("paging",paging);
		mav.setViewName("/board/freeBoard/view");
		
		return mav;
	}
	
	
	
	// ��� �����ϱ�
	@RequestMapping("/replyDelete")
	public ModelAndView replyDelete(@RequestParam("r_index") int r_index, @RequestParam("b_index") int b_index, HttpServletRequest request, ReplyPageCriteria pc) {
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO)session.getAttribute("member");
		String memId = member.getMemId();
		
		r_service.delete(r_index, memId);
		
		FBoardVO boardVO = b_service.view(b_index);
		
		List<FReplyVO> list = r_service.listAll(boardVO.getB_index(), pc);
		int listCnt = list.size();
		
		mav.addObject("view", boardVO);
		mav.addObject("viewReply", list);
		mav.addObject("viewReplyCnt", listCnt);
		mav.setViewName("/board/freeBoard/view");
		
		return mav;
	}
	
}









