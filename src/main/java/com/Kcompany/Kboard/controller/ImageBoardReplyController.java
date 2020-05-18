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
import com.Kcompany.Kboard.service.IBoardService;
import com.Kcompany.Kboard.service.IReplyService;
import com.Kcompany.Kboard.vo.MemberVO;
import com.Kcompany.Kboard.vo.IBoardVO;
import com.Kcompany.Kboard.vo.IReplyVO;

@Controller
public class ImageBoardReplyController {	
	
	@Autowired
	private IReplyService r_service;
	
	@Autowired
	private IBoardService b_service;
	
	
	// ��� ����
	
	@RequestMapping("/imageReplyWrite")
	public ModelAndView replyWrite(IReplyVO reply, HttpServletRequest request, ReplyPageCriteria pc) {

		ModelAndView mav = new ModelAndView();
		// index�� �� �������� index�̴�.
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO)session.getAttribute("member");
		String sessionId = member.getMemId();
		
		r_service.write(reply, sessionId);

		//�̰Ŵ� board�� view�̴�.
		IBoardVO boardVO = b_service.view(reply.getB_index());
		
		List<IReplyVO> list = r_service.listAll(boardVO.getB_index(), pc);
		int listCnt = list.size();
		
		ReplyPaging paging = new ReplyPaging();
		paging.setPaging(pc);
		int totalPageNum = r_service.totalCount(boardVO.getB_index());
		paging.setTotalCount(totalPageNum);
		
		mav.addObject("view", boardVO);
		mav.addObject("viewReply", list);
		mav.addObject("viewReplyCnt", listCnt);
		mav.addObject("paging",paging);
		mav.setViewName("/board/imageBoard/view");
		
		return mav;
	}
	
	
	
	// ��� �����ϱ�
	@RequestMapping("/imageReplyCorrect")
	public ModelAndView replyCorrect(IReplyVO reply, HttpServletRequest request, ReplyPageCriteria pc) {
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO)session.getAttribute("member");
		String memId = member.getMemId();
		
		r_service.correct(reply, memId);
		
		IBoardVO boardVO = b_service.view(reply.getB_index());
		
		List<IReplyVO> list = r_service.listAll(boardVO.getB_index(), pc);
		int listCnt = list.size();
		
		ReplyPaging paging = new ReplyPaging();
		paging.setPaging(pc);
		int totalPageNum = r_service.totalCount(boardVO.getB_index());
		paging.setTotalCount(totalPageNum);
		
		mav.addObject("view", boardVO);
		mav.addObject("viewReply", list);
		mav.addObject("viewReplyCnt", listCnt);
		mav.addObject("paging",paging);
		mav.setViewName("/board/imageBoard/view");
		
		return mav;
	}
	
	
	
	// ��� �����ϱ�
	@RequestMapping("/imageReplyDelete")
	public ModelAndView replyDelete(@RequestParam("r_index") int r_index, @RequestParam("b_index") int b_index, HttpServletRequest request, ReplyPageCriteria pc) {
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO)session.getAttribute("member");
		String memId = member.getMemId();
		
		r_service.delete(r_index, memId);
		
		IBoardVO boardVO = b_service.view(b_index);
		
		List<IReplyVO> list = r_service.listAll(boardVO.getB_index(), pc);
		int listCnt = list.size();
		
		mav.addObject("view", boardVO);
		mav.addObject("viewReply", list);
		mav.addObject("viewReplyCnt", listCnt);
		mav.setViewName("/board/imageBoard/view");
		
		return mav;
	}
	
}









