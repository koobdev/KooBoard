package com.Kcompany.Kboard.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.Kcompany.Kboard.service.MemberService;
import com.Kcompany.Kboard.vo.MemberVO;

@Controller
public class MemberController {
	
	@Autowired
	MemberService service;
	
	
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
	public String memLoginForm(@ModelAttribute("loginMember") MemberVO member) {
		
		return "/member/login";
	}
	
	@RequestMapping(value = "/memLogin", method = RequestMethod.POST)
	public String memLogin(@ModelAttribute("loginMember") MemberVO member, HttpSession session) { //select

		MemberVO mem = service.memSearch(member);
		
		if(mem == null) {
			return "/member/memLoginError";
		}
		else {
			session.setAttribute("member", mem);
			return "/member/memLoginOK";
		}
	}
	
	// Join Controller
	@RequestMapping("/memJoinForm")
	public String memJoinForm(@ModelAttribute("joinMember") MemberVO member) {
		
		return "/member/join";
	}
	
	@RequestMapping(value = "/memJoin", method = RequestMethod.POST)
	public String memJoin(@Valid @ModelAttribute("joinMember") MemberVO member, BindingResult result) { //insert
		
		if(result.hasErrors()) {
			return "/member/join";
		}
		
		MemberVO mem = service.memRegister(member);
		if(mem == null) {
			return "/member/memJoinError";
		}
		else {
			return "/member/memJoinOK";
		}
		
	}
	
	
	// Modify Controller
	@RequestMapping(value = "/memModifyForm", method = RequestMethod.POST)
	public String memModifyForm(@ModelAttribute("correctMember") MemberVO member) {
		MemberVO memberVO = service.memSearchById(member);
		member.setMemMail(memberVO.getMemMail());
		member.setMemPhone(memberVO.getMemPhone());
		
		return "/member/modify";
	}
	
	@RequestMapping(value = "/memModify", method = RequestMethod.POST)
	public String memModify(@Valid @ModelAttribute("correctMember") MemberVO member, BindingResult result) { //update
		
		if(result.hasErrors()) {
			return "/member/modify";
		}
		
		
		MemberVO mem = service.memModify(member);
		if(mem == null) {
			return "/member/memModifyError";
		}else {
			return "/member/memModifyOK";
		}
		
	}
	
	
	
	// Remove Controller
	@RequestMapping("/memRemoveForm")
	public String memRemoveForm(MemberVO member) {
		
		return "/member/remove";
	}
	
	@RequestMapping(value = "/memRemove", method = RequestMethod.POST)
	public String memRemove(MemberVO member, HttpServletRequest request) { //delete
			
		HttpSession session = request.getSession();
		MemberVO memSession = (MemberVO) session.getAttribute("member");
		
		// 세션 아이디와 입력한 아이디가 일치하고,
		if(member.getMemId().equals(memSession.getMemId())) {
			int result = service.memDelete(member);
			// 입력한 아이디와 비밀번호가 회원정보와 맞으면
			if(result == 1) {
				session.invalidate();
				// ok
				return "/member/memRemoveOK";
			}else {
				return "/member/memRemoveError";
			}
		}else {
			return "/member/memRemoveError";
		}
		
	}
	
	
	// // Logout Controller
	@RequestMapping("/memLogout")
	public String memLogout(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		session.invalidate();
		
		return "/member/memLogoutOK";
	}
}
