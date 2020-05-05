package com.Kcompany.Kboard.service;

import java.util.List;

import com.Kcompany.Kboard.common.paging.BoardPageCriteria;
import com.Kcompany.Kboard.vo.BoardVO;

public interface IBoardService {

	
	List<BoardVO> listAll(BoardPageCriteria pc);
	
	public int totalCount();
	
	BoardVO view(int index);	
	
	BoardVO simpleRead(int index);
	
	BoardVO write(BoardVO board, String writer);
	
	int correct(BoardVO board, String writer);
	
	void delete(int b_index);
	
	BoardVO recommand(int b_index);
	
}
