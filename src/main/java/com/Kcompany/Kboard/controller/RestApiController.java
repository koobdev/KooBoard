package com.Kcompany.Kboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Kcompany.Kboard.service.MemberService;

@RestController
public class RestApiController {

	@Autowired
	MemberService memService;
	
	// 중복된 아이디가 있는지 검사한다. 
	// @RestController를 사용하여 문자열 자체를 반환한다.
	@RequestMapping("/memIdCheck/{memId}")
	String checkMemExist(@PathVariable String memId) {
		
		boolean chk = memService.checkMemId(memId);
		
		return chk + "";
	}
	
}
