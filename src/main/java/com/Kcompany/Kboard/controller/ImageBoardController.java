package com.Kcompany.Kboard.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.Kcompany.Kboard.common.paging.IBoardPageCriteria;
import com.Kcompany.Kboard.common.paging.IBoardPaging;
import com.Kcompany.Kboard.common.paging.ReplyPageCriteria;
import com.Kcompany.Kboard.common.paging.ReplyPaging;
import com.Kcompany.Kboard.service.IReplyService;
import com.Kcompany.Kboard.service.IBoardService;
import com.Kcompany.Kboard.vo.MemberVO;

import com.Kcompany.Kboard.vo.IBoardVO;
import com.Kcompany.Kboard.vo.IReplyVO;

@Controller
public class ImageBoardController {

	@Autowired
	private IBoardService b_service;
	
	@Autowired
	private IReplyService r_service;
	
	@Autowired
	private IBoardPaging b_paging;
	
	@Autowired
	private ReplyPaging r_paging;

	
	// 글 목록 열기
	@RequestMapping("/imageOpenList")
	public ModelAndView openList(IBoardPageCriteria pc) {
		ModelAndView mav = new ModelAndView();
		List<IBoardVO> list = b_service.listAll(pc);
		
		// 게시글 페이징 처리
		b_paging.setPage(pc);
		int totalPageNum = b_service.totalCount();
		b_paging.setTotalCount(totalPageNum);
		
		// Request영역에 Attribute저장
		mav.addObject("list", list);
		mav.addObject("paging", b_paging);
		mav.setViewName("/board/imageBoard/list");
		return mav;
	}
	
	// 글 목록에서 게시글 선택	
	@RequestMapping(value="/imageOpenContent", method = RequestMethod.GET)
	public ModelAndView openContent(@RequestParam("b_index")int index, ReplyPageCriteria pc) {
		ModelAndView mav = new ModelAndView();
		IBoardVO boardVO = b_service.view(index);
				
		// 해당 게시글의 index를 이용해서 댓글을 리스트 형태로 보여준다.
		List<IReplyVO> list = r_service.listAll(boardVO.getB_index(), pc);
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
		mav.setViewName("/board/imageBoard/view");
		return mav;
	}
	
	// 게시글 추천하기
	@RequestMapping("/imageRecommand")
	public ModelAndView recommand(@RequestParam("b_index")int index, ReplyPageCriteria pc) {
		ModelAndView mav = new ModelAndView();
		b_service.recommand(index);
		IBoardVO boardvo = b_service.simpleRead(index);
		
		// 댓글을 리스트의 형태로 보여준다.
		List<IReplyVO> list = r_service.listAll(boardvo.getB_index(),pc);
		int listCnt = list.size();
		
		// Request영역에 Attribute저장
		mav.addObject("view", boardvo);
		mav.addObject("viewReply", list);
		mav.addObject("viewReplyCnt", listCnt);
		mav.setViewName("/board/imageBoard/view");
		return mav;
	}
	
	// 게시글 작성하기 폼
	@RequestMapping("/imageWriteForm")
	public String writeForm(@ModelAttribute("IWrite") IBoardVO board) {
		return "/board/imageBoard/write";
	}
	// 게시글 작성하기
	@RequestMapping(value="/imageWriteBoard", method=RequestMethod.POST)
	public String writeBoard(@Valid @ModelAttribute("IWrite")IBoardVO board, Model model, BindingResult result) {
		
		// 요청에서 에러가 발생하면 게시글 작성하기 폼을 이용하여 에러 결과 표출
		if(result.hasErrors()) {
			return "/board/imageBoard/write";
		}
		
		// 이미지 첨부를 하면 작성처리
		if(board.getUploadFile().getSize() > 0) {
			IBoardVO iresult = b_service.write(board);
			IBoardVO boardVO = b_service.view(iresult.getB_index());
			model.addAttribute("view",boardVO);
			
			return "/board/imageBoard/view";
		}
		// 이미지 첨부를 하지 않으면 Error페이지로 이동
		else {
			return "/board/imageBoard/writeError";
		}
		
	}
	
	// 게시글 수정하기 폼
	@RequestMapping("/imageCorrectBoardForm")
	public String correctBoardForm(Model model, @RequestParam("b_index") int index, HttpServletRequest request) {
		
		// correctView로 현재글의 내용을 수정 페이지와 함께 보여줌.
		IBoardVO boardVO = b_service.simpleRead(index);
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		String sessionId = memberVO.getMemId();
		
		// 글 작성자와 현재 로그인된 사용자의 아이디가 다르면 수정할 수 없다.
		if(boardVO.getB_memId().equals(sessionId)) {
			model.addAttribute("correctView", boardVO);
			return "/board/imageBoard/correct";
		}else {
			System.out.println("Error!!!!!!!!!!!!!!!!!!!");
			return "redirect:/board/imageBoard/correctError";
		}
	}
	// 게시글 수정하기
	@RequestMapping("/imageCorrectBoard")
	public String correctBoard(@Valid @ModelAttribute("correctView")IBoardVO board, HttpServletRequest request, 
							   ReplyPageCriteria pc, Model model, BindingResult result) {
		
		// 요청에서 에러가 발생하면 게시글 수정하기 폼을 이용하여 에러 결과 표출
		if(result.hasErrors()) {
			return "/board/imageBoard/correct";
		}
		
		// 이미지 첨부를 하면 수정처리
		if(board.getUploadFile().getSize() > 0) {
			int iresult = b_service.correct(board);
			if(iresult != 1) return "redirect:/board/imageBoard/correctError";
			
			IBoardVO boardVO = b_service.view(board.getB_index());
			
			// 댓글을 리스트의 형태로 보여준다.		
			List<IReplyVO> list = r_service.listAll(boardVO.getB_index(), pc);
			int listCnt = list.size();
			
			// Request영역에 Attribute저장
			model.addAttribute("view", boardVO);
			model.addAttribute("viewReply", list);
			model.addAttribute("viewReplyCnt", listCnt);
			return "/board/imageBoard/view";
		}
		// 이미지 첨부를 하지 않으면 Error페이지로 이동
		else {
			return "/board/imageBoard/writeError";
		}
	}
	
	
	// 게시글 삭제하기		
	@RequestMapping("imageDeleteBoard")
	public String deleteBoard(@RequestParam("b_index") int b_index) {
		b_service.delete(b_index);
		
		return "redirect:/imageOpenList";
	}

}















