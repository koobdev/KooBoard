package com.Kcompany.Kboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Kcompany.Kboard.common.paging.ReplyPageCriteria;
import com.Kcompany.Kboard.dao.ReplyDAO;
import com.Kcompany.Kboard.vo.ReplyVO;

@Service
public class ReplyService implements IReplyService{

	@Autowired
	ReplyDAO dao;
	
	public List<ReplyVO> listAll(int index, ReplyPageCriteria pc) {
		List<ReplyVO> list = dao.selectlist(index, pc);
		
		return list;
	}
	
	public int totalCount(int index) {
		
		int result = dao.totalCount(index);
		return result;
	}
	
	public ReplyVO view(ReplyVO reply, int index) {
		ReplyVO replyVO = dao.select(reply, index);
		
		
		return replyVO;
	}
	
	public int write(ReplyVO reply, String memId) {
		
		int result = dao.insert(reply, memId);
		if(result != 1) {
			System.out.println("ReplyService : write() Error!!");
		}
		return result;
	}
	
	public int correct(ReplyVO reply, String memId) {
		
		int result = dao.update(reply, memId);
		if(result != 1) {
			System.out.println("ReplyService : correct() Error!!");
		}
		return result;
	}

	public int delete(int r_index, String memId) {
		
		int result = dao.delete(r_index, memId);
		if(result != 1) {
			System.out.println("ReplyService : delete() Error!!");
		}
		return result;
	}
}
