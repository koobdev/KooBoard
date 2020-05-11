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
	
	@RequestMapping("/memIdCheck/{memId}")
	String checkMemExist(@PathVariable String memId) {
		
		boolean chk = memService.checkMemId(memId);
		
		return chk + "";
	}
}
