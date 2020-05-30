package com.Kcompany.Kboard.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Kcompany.Kboard.common.paging.ReplyPageCriteria;
import com.Kcompany.Kboard.mapper.FReplyMapper;
import com.Kcompany.Kboard.vo.FReplyVO;

@Repository
public class FReplyDAO {
	
	@Autowired
	private FReplyMapper repMapper;
	
	
	public List<FReplyVO> selectlist(int index, ReplyPageCriteria pc){
		// 쿼리에 들어갈 페이지 시작번호를 가져옴
		pc.setPageStart();
		return repMapper.selectlist(index, pc);
	}
	
	public int totalCount(int index) {
		return repMapper.totalCount(index);
	}
	
	public int recentIndex() {
		return repMapper.recentIndex();
	}
	
	public int insert(FReplyVO reply, String sessionId) {
		return repMapper.insert(reply, sessionId);
	}
	
	public int update(FReplyVO reply, String sessionId) {
		return repMapper.update(reply, sessionId);
	}

	public int delete(int r_index, String sessionId) {
		return repMapper.delete(r_index, sessionId);
	}

	public int deleteBoard(int b_index) {
		return repMapper.deleteBoard(b_index);
	}

	
}















