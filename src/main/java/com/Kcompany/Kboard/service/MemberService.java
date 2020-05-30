package com.Kcompany.Kboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Kcompany.Kboard.dao.MemberDAO;
import com.Kcompany.Kboard.vo.MemberVO;

@Service
public class MemberService{
	
	@Autowired
	private MemberDAO dao;
	
	
	public MemberVO memSearch(MemberVO member) { 
		MemberVO mem = dao.select(member);
		return mem;
	}
	
	public MemberVO memSearchById(MemberVO member) { 
		MemberVO mem = dao.selectById(member);
		return mem;
	}
	
	public MemberVO memRegister(MemberVO member) { 
		if(dao.insert(member) != 1) {
			System.out.println("memRegister Error!");
		}
		return member;
	}
	
	public MemberVO memModify(MemberVO member) { 
		if(dao.update(member) != 1) {
			System.out.println("memModify Error");
		}
		return member;
	}
	
	public int memDelete(MemberVO memSession) { 
		int result = dao.delete(memSession);
		if(dao.delete(memSession) != 1) {
			System.out.println("memDelete Error!");
		}
		return result;
	}
	
	public boolean checkMemId(String memId) {
		String check = dao.checkMemId(memId);
		
		if(check == null) {
			return true;
		}else return false;
	}
}










