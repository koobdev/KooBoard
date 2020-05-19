package com.Kcompany.Kboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Kcompany.Kboard.common.paging.ReplyPageCriteria;
import com.Kcompany.Kboard.dao.IReplyDAO;
import com.Kcompany.Kboard.vo.IReplyVO;

@Service
public class IReplyService{

	@Autowired
	IReplyDAO dao;

	
	public List<IReplyVO> listAll(int index, ReplyPageCriteria pc) {
		List<IReplyVO> list = dao.selectlist(index, pc);
		return list;
	}
	
	public int totalCount(int index) {
		int result = dao.totalCount(index);
		return result;
	}
	
	public int write(IReplyVO reply, String sessionId) {
		int recIndex = dao.recentIndex();
		reply.setR_index(++recIndex);
		int result = dao.insert(reply, sessionId);
		if(result != 1) {
			System.out.println("ReplyService : write() Error!!");
		}
		return result;
	}
	
	public int correct(IReplyVO reply, String sessionId) {
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
