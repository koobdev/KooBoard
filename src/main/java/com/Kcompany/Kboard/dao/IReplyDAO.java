package com.Kcompany.Kboard.dao;

import java.util.List;

import com.Kcompany.Kboard.common.paging.ReplyPageCriteria;
import com.Kcompany.Kboard.vo.ReplyVO;

public interface IReplyDAO {
	
	List<ReplyVO> selectlist(int index, ReplyPageCriteria pc);
	
	int totalCount(int index);
	
	ReplyVO select(ReplyVO replyVO, int index);
	
	int recentIndex();
	
	int insert(ReplyVO reply, String memId);
	
	int update(ReplyVO reply, String memId);
	
	int delete(int r_index, String memId);

}
