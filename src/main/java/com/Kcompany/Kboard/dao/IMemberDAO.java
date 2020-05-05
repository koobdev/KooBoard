package com.Kcompany.Kboard.dao;

import com.Kcompany.Kboard.vo.MemberVO;

public interface IMemberDAO {
	
	MemberVO select(MemberVO member);
	
	int insert(MemberVO member);
	
	int update(MemberVO member, MemberVO memSession);
	
	int delete(MemberVO memberSession);
}
