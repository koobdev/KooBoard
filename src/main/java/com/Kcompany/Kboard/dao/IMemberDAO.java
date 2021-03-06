package com.Kcompany.Kboard.dao;

import com.Kcompany.Kboard.vo.MemberVO;

public interface IMemberDAO {
	
	MemberVO select(MemberVO member);
	
	MemberVO selectById(MemberVO member);
	
	int insert(MemberVO member);
	
	int update(MemberVO member);
	
	int delete(MemberVO member);
	
	String checkMemId(String memId);
}
