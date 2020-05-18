package com.Kcompany.Kboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Kcompany.Kboard.common.paging.ReplyPageCriteria;
import com.Kcompany.Kboard.dao.FReplyDAO;
import com.Kcompany.Kboard.vo.FReplyVO;

@Service
public class FReplyService{

	@Autowired
	FReplyDAO dao;

	
	public List<FReplyVO> listAll(int index, ReplyPageCriteria pc) {
		List<FReplyVO> list = dao.selectlist(index, pc);
		
		return list;
	}
	
	public int totalCount(int index) {
		
		int result = dao.totalCount(index);
		return result;
	}
	
	
	public int write(FReplyVO reply, String sessionId) {
		
		int recIndex = dao.recentIndex();
		reply.setR_index(++recIndex);
		int result = dao.insert(reply, sessionId);
		if(result != 1) {
			System.out.println("ReplyService : write() Error!!");
		}
		return result;
	}
	
	public int correct(FReplyVO reply, String sessionId) {
		
		int result = dao.update(reply, sessionId);
		if(result != 1) {
			System.out.println("ReplyService : correct() Error!!");
		}
		return result;
	}

	public int delete(int r_index, String sessionId) {
		
		int result = dao.delete(r_index, sessionId);
		if(result != 1) {
			System.out.println("ReplyService : delete() Error!!");
		}
		return result;
	}
}
