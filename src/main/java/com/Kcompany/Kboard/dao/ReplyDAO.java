package com.Kcompany.Kboard.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Kcompany.Kboard.common.paging.ReplyPageCriteria;
import com.Kcompany.Kboard.mapper.ReplyMapper;
import com.Kcompany.Kboard.vo.ReplyVO;

@Repository
public class ReplyDAO implements IReplyDAO {

	
	@Autowired
	private ReplyMapper repMapper;
	
	
	public List<ReplyVO> selectlist(int index, ReplyPageCriteria pc){
		pc.setPageStart();
		return repMapper.selectlist(index, pc);
	}
	
	public int totalCount(int index) {
		return repMapper.totalCount(index);
	}
	
	public int recentIndex() {
		return repMapper.recentIndex();
	}
	
	/*public ReplyVO select(ReplyVO replyVO, int index) {
		return
	}*/
	
	public int insert(ReplyVO reply, String sessionId) {
		return repMapper.insert(reply, sessionId);
	}
	
	public int update(ReplyVO reply, String sessionId) {
		return repMapper.update(reply, sessionId);
	}

	public int delete(int r_index, String sessionId) {
		return repMapper.delete(r_index, sessionId);
	}

	public int deleteBoard(int b_index) {
		return repMapper.deleteBoard(b_index);
	}

	
}















