package com.Kcompany.Kboard.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Kcompany.Kboard.common.paging.ReplyPageCriteria;
import com.Kcompany.Kboard.mapper.IReplyMapper;
import com.Kcompany.Kboard.vo.IReplyVO;

@Repository
public class IReplyDAO {

	
	@Autowired
	private IReplyMapper repMapper;
	
	
	public List<IReplyVO> selectlist(int index, ReplyPageCriteria pc){
		pc.setPageStart();
		return repMapper.selectlist(index, pc);
	}
	
	public int totalCount(int index) {
		return repMapper.totalCount(index);
	}
	
	public int recentIndex() {
		return repMapper.recentIndex();
	}
		
	public int insert(IReplyVO reply, String sessionId) {
		return repMapper.insert(reply, sessionId);
	}
	
	public int update(IReplyVO reply, String sessionId) {
		return repMapper.update(reply, sessionId);
	}

	public int delete(int r_index, String sessionId) {
		return repMapper.delete(r_index, sessionId);
	}

	public int deleteBoard(int b_index) {
		return repMapper.deleteBoard(b_index);
	}

	
}















