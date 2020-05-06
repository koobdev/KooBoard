package com.Kcompany.Kboard.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.Kcompany.Kboard.common.paging.BoardPageCriteria;
import com.Kcompany.Kboard.mapper.BoardMapper;
import com.Kcompany.Kboard.vo.BoardVO;

@Repository
public class BoardDAO implements IBoardDAO {

	@Autowired
	private BoardMapper boardMapper;
	
	public List<BoardVO> list(BoardPageCriteria pc){
		pc.setPageStart();
		return boardMapper.list(pc);
	}
	
	public int total() {
		return boardMapper.total();
	}
	
	public BoardVO read(int index) {
		return boardMapper.read(index);
	}

	public int hitCnt(BoardVO board) {
		return boardMapper.hitCnt(board);
	}
	
	public int recentIndex() {
		return boardMapper.recentIndex();
	}

	public int insert(BoardVO board) {
		return boardMapper.insert(board);
	}

	public int update(BoardVO board) {
		return boardMapper.update(board);
	}

	public int delete(int b_index) {
		return boardMapper.delete(b_index);
	}

	public BoardVO recommandCnt(int index) {
		return boardMapper.read(index);
	}

}
