package com.Kcompany.Kboard.service;

import java.util.List;

import com.Kcompany.Kboard.common.paging.ReplyPageCriteria;
import com.Kcompany.Kboard.vo.ReplyVO;

public interface IReplyService {

	List<ReplyVO> listAll(int index, ReplyPageCriteria pc);
	
	int totalCount(int index);
	
/*	ReplyVO view(ReplyVO reply, int index);
*/	
	int write(ReplyVO reply, String sessionId);
	
	int correct(ReplyVO reply, String sessionId);
	
	int delete(int r_index, String sessionId);
	
}
