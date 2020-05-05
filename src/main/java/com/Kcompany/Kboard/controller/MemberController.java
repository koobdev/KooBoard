package com.Kcompany.Kboard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.Kcompany.Kboard.service.MemberService;
import com.Kcompany.Kboard.vo.MemberVO;

@Controller
public class MemberController {
	
	//객체 자동주입
	@Autowired
	MemberService service;
	
	
	//contextpath를 ModelAttribute를 통해 controller가 호출될때 항상 사용할 수 있도록 함.
	@ModelAttribute("cp")
	public String classpath(HttpServletRequest request) {
			
		String cp = request.getContextPath();
		return cp;
	}
	
	
	@RequestMapping("/viewForm")
	public String viewForm(MemberVO member) {
		
		return "board/view";
	}

	// Login Controller
	@RequestMapping("/memLoginForm")
	public String memLoginForm(MemberVO member) {
		
		return "/member/login";
	}
	
	@RequestMapping(value = "/memLogin", method = RequestMethod.POST)
	public ModelAndView memLogin(MemberVO member, HttpSession session) { //select
		
		ModelAndView mav = new ModelAndView();
		MemberVO mem = service.memSearch(member);
		session.setAttribute("member", mem);
		
		if(mem == null) {
			mav.addObject("member", mem);
			mav.setViewName("/member/memLoginError");
		}else {
			mav.addObject("member", mem);
			mav.setViewName("/member/memLoginOK");
		}
		
		return mav;
	}
	
	
	
	// Join Controller
	@RequestMapping("/memJoinForm")
	public String memJoinForm(MemberVO member) {
		
		return "/member/join";
	}
	
	@RequestMapping(value = "/memJoin", method = RequestMethod.POST)
	public ModelAndView memJoin(MemberVO member) { //insert
			
		ModelAndView mav = new ModelAndView();
		MemberVO mem = service.memRegister(member);
				
		mav.addObject("member", mem);
		mav.setViewName("/member/memJoinOK");
		
		return mav;
	}
	
	
	
	// Modify Controller
	@RequestMapping("/memModifyForm")
	public String memModifyForm(MemberVO member) {
		
		return "/member/modify";
	}
	
	@RequestMapping(value = "/memModify", method = RequestMethod.POST)
	public ModelAndView memModify(MemberVO member, HttpServletRequest request) { //update
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO memSession = (MemberVO) session.getAttribute("member");
		MemberVO mem = service.memModify(member, memSession);
		
		mav.addObject("member", mem);
		mav.setViewName("/member/memModifyOK");
		
		return mav;
	}
	
	
	
	// Remove Controller
	@RequestMapping("/memRemoveForm")
	public String memRemoveForm(MemberVO member) {
		
		return "/member/remove";
	}
	
	@RequestMapping(value = "/memRemove", method = RequestMethod.POST)
	public ModelAndView memRemove(MemberVO member, HttpServletRequest request) { //delete
			
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO memSession = (MemberVO) session.getAttribute("member");
		MemberVO mem = service.memDelete(memSession);
		

		mav.addObject("member", mem);
		mav.setViewName("/member/memRemoveOK");
		
		session.invalidate();
		return mav;
	}
	
	
	// // Logout Controller
	@RequestMapping("/memLogout")
	public String memLogout(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		session.invalidate();
		
		return "/member/memLogoutOK";
	}
}
