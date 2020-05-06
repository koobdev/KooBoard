package com.Kcompany.Kboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Kcompany.Kboard.dao.MemberDAO;
import com.Kcompany.Kboard.vo.MemberVO;

@Service
public class MemberService{
	
	@Autowired
	private MemberDAO dao;
	
	
	public MemberVO memSearch(MemberVO member) { //select
		MemberVO mem = dao.select(member);
		return mem;
	}
	
	public MemberVO memRegister(MemberVO member) { //insert
		if(dao.insert(member) != 1) {
			System.out.println("memRegister Error!");
		}
		return member;
	}
	
	public MemberVO memModify(MemberVO member) { //update
		if(dao.update(member) != 1) {
			System.out.println("memModify Error");
		}
		return member;
	}
	
	public MemberVO memDelete(MemberVO memSession) { //delete
		if(dao.delete(memSession) != 1) {
			System.out.println("memDelete Error!");
		}
		return memSession;
	}
}










