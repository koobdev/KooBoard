package com.Kcompany.Kboard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Kcompany.Kboard.service.MemberService;
import com.Kcompany.Kboard.vo.MemberVO;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	
/*	@ModelAttribute("cp")
	public String classpath(HttpServletRequest request) {
		String cp = request.getContextPath();
		return cp;
	}
	*/
	

	// 로그인 폼
	@RequestMapping("/memLoginForm")
	public String memLoginForm(@ModelAttribute("loginMember") MemberVO member) {
		return "/member/login";
	}
	// 로그인 하기
	@RequestMapping(value = "/memLogin", method = RequestMethod.POST)
	public String memLogin(@ModelAttribute("loginMember") MemberVO member, HttpSession session) { 
		MemberVO mem = service.memSearch(member);
		
		// 객체에 회원정보를 담아오지 못하면 Error페이지로 이동
		if(mem == null) {
			return "/member/memLoginError";
		}
		else {
			session.setAttribute("member", mem);
			return "/member/memLoginOK";
		}
	}
	
	// 회원가입 폼
	@RequestMapping("/memJoinForm")
	public String memJoinForm(@ModelAttribute("joinMember") MemberVO member) {
		
		return "/member/join";
	}
	// 회원가입 하기
	@RequestMapping(value = "/memJoin", method = RequestMethod.POST)
	public String memJoin(@Valid @ModelAttribute("joinMember") MemberVO member, BindingResult result) { 
		
		// 요청에서 에러가 발생하면 회원가입 폼을 이용하여 에러 결과 표출
		if(result.hasErrors()) {
			return "/member/join";
		}
		
		MemberVO mem = service.memRegister(member);
		
		// 객체에 회원정보를 담아오지 못하면 Error페이지로 이동
		if(mem == null) {
			return "/member/memJoinError";
		}
		else {
			return "/member/memJoinOK";
		}
	}
	
	// 회원정보 수정 폼
	@RequestMapping(value = "/memModifyForm", method = RequestMethod.POST)
	public String memModifyForm(@ModelAttribute("correctMember") MemberVO member) {
		
		// 이름으로 회원정보를 찾아서 회원정보 수정 폼에 뿌려줌
		MemberVO memberVO = service.memSearchById(member);
		member.setMemMail(memberVO.getMemMail());
		member.setMemPhone(memberVO.getMemPhone());
		return "/member/modify";
	}
	// 회원정보 수정하기
	@RequestMapping(value = "/memModify", method = RequestMethod.POST)
	public String memModify(@Valid @ModelAttribute("correctMember") MemberVO member, BindingResult result) { 
		
		// 요청에서 에러가 발생하면 회원가입 폼을 이용하여 에러 결과 표출
		if(result.hasErrors()) {
			return "/member/modify";
		}
		
		MemberVO mem = service.memModify(member);
		
		// 객체에 회원정보를 업데이트하지 못하면 Error페이지로 이동
		if(mem == null) {
			return "/member/memModifyError";
		}else {
			return "/member/memModifyOK";
		}
	}
	
	// 회원정보 삭제 폼
	@RequestMapping("/memRemoveForm")
	public String memRemoveForm(MemberVO member) {
		return "/member/remove";
	}
	// 회원정보 삭제하기
	@RequestMapping(value = "/memRemove", method = RequestMethod.POST)
	public String memRemove(MemberVO member, HttpServletRequest request) { 
		
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
	
	// 로그아웃 하기
	@RequestMapping("/memLogout")
	public String memLogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		// 세션을 무효화시킴
		session.invalidate();
		return "/member/memLogoutOK";
	}
}
