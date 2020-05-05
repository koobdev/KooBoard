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
	
	int insert(BoardVO board, String writer);
	
	int update(BoardVO board, String writer);
	
	int delete(int b_index);
	
	BoardVO recommandCnt(int index);
	
	
}
