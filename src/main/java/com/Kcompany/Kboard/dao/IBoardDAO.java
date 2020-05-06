package com.Kcompany.Kboard.dao;

import java.util.List;

import com.Kcompany.Kboard.common.paging.BoardPageCriteria;
import com.Kcompany.Kboard.vo.BoardVO;

public interface IBoardDAO {
	
	List<BoardVO> list(BoardPageCriteria pc);
	
	public int total();
	
	BoardVO read(int index);
	
	int hitCnt(BoardVO board);
	
	int recentIndex();
	
	int insert(BoardVO board);
	
	int update(BoardVO board);
	
	int delete(int b_index);
	
	BoardVO recommandCnt(int index);
	
	
}
