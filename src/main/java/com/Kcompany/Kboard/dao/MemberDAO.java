package com.Kcompany.Kboard.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Kcompany.Kboard.mapper.MemberMapper;
import com.Kcompany.Kboard.vo.MemberVO;

@Repository
public class MemberDAO {

	@Autowired
	MemberMapper memMapper;
	
	
	public MemberVO select(MemberVO member) {
		return memMapper.select(member);
	}
	
	public MemberVO selectById(MemberVO member) {
		return memMapper.selectById(member);
	}
	
	public int insert(MemberVO member) {
		return memMapper.insert(member);
	}
	
	public int update(MemberVO member) {
		return memMapper.update(member);
	}
	
	public int delete(MemberVO member) {
		return memMapper.delete(member);
	}
	
	public String checkMemId(String memId) {
		return memMapper.checkMemId(memId);
	}
}















