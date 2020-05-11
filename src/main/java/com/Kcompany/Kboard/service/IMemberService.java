package com.Kcompany.Kboard.service;

import com.Kcompany.Kboard.vo.MemberVO;

public interface IMemberService {

	MemberVO memSearch(MemberVO member);
	
	MemberVO memSearchById(MemberVO member);
	
	MemberVO memRegister(MemberVO member);

	MemberVO memModify(MemberVO member, MemberVO memSession);
	
	int memDelete(MemberVO memSession);
}
